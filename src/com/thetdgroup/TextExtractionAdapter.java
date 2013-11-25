package com.thetdgroup;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.input.BOMInputStream;

import org.jdom.Document;
import org.json.JSONObject;
import org.xml.sax.ContentHandler;

import com.thetdgroup.extraction_utils.ContentInformation;
import com.thetdgroup.AdapterConstants;

public class TextExtractionAdapter extends BaseTextExtractionAdapter
{
	private FuzeInCommunication fuzeInCommunication = new FuzeInCommunication();

	public void initialize(final JSONObject configurationObject) throws Exception
	{
		if(configurationObject.has("adapter_configuration_file") == false)
		{
			throw new Exception("The adapter_configuration_file parameter was not found");
		}
		
		//
		if(configurationObject.has("fuzein_connection_info"))
		{
			JSONObject jsonCommParams = configurationObject.getJSONObject("fuzein_connection_info");
			
			fuzeInCommunication.setFuzeInConnection(jsonCommParams.getString("service_url"), 
																																											jsonCommParams.getInt("service_socket_timeout"), 
																																											jsonCommParams.getInt("service_connection_timeout"), 
																																											jsonCommParams.getInt("service_connection_retry"));
		}
		
		//
		parseAdapterConfiguration(configurationObject.getString("adapter_configuration_file"));
	}
	
	//
	public void destroy()
	{
	 if(fuzeInCommunication != null)
	 {
	 	fuzeInCommunication.releaseConnection();
	 }
	}
	
	//
	public JSONObject extractContent(JSONObject parameters) throws Exception
	{
		//
		// Validate that all required params are present
		//
		if(parameters.has("file_name") == false)
		{
			throw new Exception("The 'file_name' parameter is required.");
		}
		
		if(parameters.has("output_type") == false)
		{
			throw new Exception("The 'output_type' parameter is required.");
		}
		
		if(parameters.getString("output_type").equals("text") == false && 
				 parameters.getString("output_type").equals("metadata") == false && 
				 parameters.getString("output_type").equals("full") == false)
		{
			throw new Exception("The 'output_type' parameter can only be 'text', 'metadata' or 'full'");
		}
		
		//
		// Check how the content is provided.
		// 1. Text 
		// 2. File reference
		// 3. Stream
		// 4. URL
		String contentSignature = "";
		String submitalType = "text";
			
		//
		if(parameters.has("submital_type"))
		{
			submitalType = parameters.getString("submital_type");
		}
		
		//
		InputStream fileInputStream = null;
		InputStream urlInputStream = null;
		
		try
		{
			if(submitalType.equals("file"))
			{
				File fileData = new File(parameters.getString("document_content")); // points to file (must be accessible by this service)


			}
			else if(submitalType.equals("string"))
			{


			}
			else if(submitalType.equals("stream"))
			{
				File fileData = new File(parameters.getString("document_content")); // points to file
			 
				Metadata metadata = new Metadata();
    metadata.set(Metadata.RESOURCE_NAME_KEY, fileData.getName());

			}
			else if(submitalType.equals("url"))
			{
				urlInputStream = new URL(parameters.getString("document_content")).openStream(); 
				
				File fileData = new File(parameters.getString("document_name"));
				FileUtils.copyInputStreamToFile(urlInputStream, fileData) ;
			 fileInputStream = new FileInputStream(fileData);
	

			}
		}
		catch(Exception exception) 
		{
   throw new Exception(exception);
	 }
	 finally 
	 {
	  if(fileInputStream != null) 
	  {
	  	fileInputStream.close();
	  }
	  
	  if(urlInputStream != null)
	  {
	  	urlInputStream.close();
	  }
	 }
		
		//
		// Extract content
		JSONObject jsonDataObject = new JSONObject();
		ContentInformation extractedContent = processFile(new File(parameters.getString("file_name")));
		
		if(parameters.getString("output_type").equals("text"))
		{
			jsonDataObject.put("text_data", extractedContent.getContentData());
		}
		else if(parameters.getString("output_type").equals("metadata"))
		{
			jsonDataObject.put("metadata", extractedContent.toJSON());
		}
		else if(parameters.getString("output_type").equals("full"))
		{
			jsonDataObject.put("text_data", extractedContent.getContentData());
			jsonDataObject.put("metadata", extractedContent.toJSON());
		}
		
	 //
		JSONObject jsonObject = new JSONObject(); 
		jsonObject.put(AdapterConstants.ADAPTER_STATUS, AdapterConstants.status.SUCCESS);
		jsonObject.put(AdapterConstants.ADAPTER_DATA, jsonDataObject);
	 
		return jsonObject;
	}
		
	//
	//
	//
 private ContentInformation processFile(File fileName) throws IOException
 {
 	ContentInformation extractedContent = new ContentInformation();
 	ContentHandler contenthandler = new BodyContentHandler();
 	Metadata metadata = new Metadata();

 	//
 	InputStream inputStream = null;
 	BOMInputStream bomInputStream = null;
 	
 	try
 	{
 		inputStream = new FileInputStream(fileName);
	 	bomInputStream = new BOMInputStream(inputStream, false);
	 	
	  contenthandler = new BodyContentHandler();  
	  metadata.set(Metadata.RESOURCE_NAME_KEY, fileName.getName());
	  
	  Parser parser = new AutoDetectParser();
	  parser.parse(bomInputStream, contenthandler, metadata);
 	}
  catch(Exception exception) 
  {  
  	extractedContent.hasException();
  	extractedContent.setException(exception.toString());
  } 
  finally 
  {
   if(bomInputStream != null)
   {
   	bomInputStream.close();
   }
   
   if(inputStream != null)
   {
    inputStream.close();
   }
  }
  
  //
  //
  extractedContent.setImportedFileName(fileName.getName());
  
  if(contenthandler != null)
  {
  	String content = contenthandler.toString().replace("\n", " ");
  	extractedContent.setContentData(content);
  }

  if(metadata != null)
  {
  	// CREATIVE COMMONS
	  extractedContent.setLicenseLocation(metadata.get(Metadata.LICENSE_LOCATION));
	  extractedContent.setLicenceURL(metadata.get(Metadata.LICENSE_URL));
	  extractedContent.setWorkType(metadata.get(Metadata.WORK_TYPE));
	  
	  // DUBLIN CORE
	  extractedContent.setContributor(metadata.get(Metadata.CONTRIBUTOR));
	  extractedContent.setCoverage(metadata.get(Metadata.COVERAGE));
	  extractedContent.setCreator(metadata.get(Metadata.CREATOR));
	  extractedContent.setDate(metadata.get(Metadata.DATE));
	  extractedContent.setDescription(metadata.get(Metadata.DESCRIPTION));
	  extractedContent.setFormat(metadata.get(Metadata.FORMAT));
	  extractedContent.setIdentifier(metadata.get(Metadata.IDENTIFIER));
	  extractedContent.setLanguage(metadata.get(Metadata.LANGUAGE));
	  extractedContent.setModified(metadata.get(Metadata.MODIFIED));
	  extractedContent.setPublisher(metadata.get(Metadata.PUBLISHER));
	  extractedContent.setRelation(metadata.get(Metadata.RELATION));
	  extractedContent.setRights(metadata.get(Metadata.RIGHTS));
	  extractedContent.setDublinSource(metadata.get(org.apache.tika.metadata.DublinCore.SOURCE));
	  extractedContent.setSubject(metadata.get(Metadata.SUBJECT));
	  extractedContent.setTitle(metadata.get(Metadata.TITLE));
	  extractedContent.setType(metadata.get(Metadata.TYPE));
	  
	  // GEOGRAPHIC
	  //extractedContent.setAltitude(metadata.get(Metadata.ALTITUDE));
	  //extractedContent.setLatitude(metadata.get(Metadata.LATITUDE));
	  //extractedContent.setLongitude(metadata.get(Metadata.LONGITUDE));
	  
	  // HTTP HEADERS
	  extractedContent.setContentDisposition(metadata.get(Metadata.CONTENT_DISPOSITION));
	  extractedContent.setContentEncoding(metadata.get(Metadata.CONTENT_ENCODING));
	  extractedContent.setContentLanguage(metadata.get(Metadata.CONTENT_LANGUAGE));
	  extractedContent.setContentLength(metadata.get(Metadata.CONTENT_LENGTH));
	  extractedContent.setContentLocation(metadata.get(Metadata.CONTENT_LOCATION));
	  extractedContent.setContentMD5(metadata.get(Metadata.CONTENT_MD5));
	  extractedContent.setContentType(metadata.get(Metadata.CONTENT_TYPE));
	  extractedContent.setLastModifier(metadata.get(Metadata.LAST_MODIFIED));
	  extractedContent.setLocation(metadata.get(Metadata.LOCATION));
	  
	  // MESSAGE (EMAIL)
	  //extractedContent.setMessageBCC(metadata.get(Metadata.MESSAGE_BCC));
	  //extractedContent.setMessageCC(metadata.get(Metadata.MESSAGE_CC));
	  //extractedContent.setMessageFrom(metadata.get(Metadata.MESSAGE_FROM));
	  //extractedContent.setMessageRecipientAddress(metadata.get(Metadata.MESSAGE_RECIPIENT_ADDRESS));
	  //extractedContent.setMessageTo(metadata.get(Metadata.MESSAGE_TO));
	  
  	// MS OFFICE
	  extractedContent.setApplicationName(metadata.get(Metadata.APPLICATION_NAME));
	  extractedContent.setApplicationVersion(metadata.get(Metadata.APPLICATION_VERSION));
	  extractedContent.setAuthor(metadata.get(Metadata.AUTHOR));
	  extractedContent.setCategory(metadata.get(Metadata.CATEGORY));
	  extractedContent.setCharacterCount(metadata.get(Metadata.CHARACTER_COUNT));
	  extractedContent.setCharacterCountWithSpace(metadata.get(Metadata.CHARACTER_COUNT_WITH_SPACES));
	  extractedContent.setComments(metadata.get(Metadata.COMMENTS));
	  extractedContent.setCompany(metadata.get(Metadata.COMPANY));
	  extractedContent.setContentStatus(metadata.get(Metadata.CONTENT_STATUS));
	  extractedContent.setCreationDate(metadata.get(Metadata.CREATION_DATE));
	  extractedContent.setEditTime(metadata.get(Metadata.EDIT_TIME));
	  extractedContent.setKeywords(metadata.get(Metadata.KEYWORDS));
	  extractedContent.setLastAuthor(metadata.get(Metadata.LAST_AUTHOR));
	  extractedContent.setLastPrinted(metadata.get(Metadata.LAST_PRINTED));
	  extractedContent.setLastSaved(metadata.get(Metadata.LAST_SAVED));
	  extractedContent.setLineCount(metadata.get(Metadata.LINE_COUNT));
	  extractedContent.setManager(metadata.get(Metadata.MANAGER));
	  extractedContent.setNotes(metadata.get(Metadata.NOTES));
	  extractedContent.setPageCount(metadata.get(Metadata.PAGE_COUNT));
	  extractedContent.setParagraphCount(metadata.get(Metadata.PARAGRAPH_COUNT));
	  extractedContent.setPresentationFormat(metadata.get(Metadata.PRESENTATION_FORMAT));
	  extractedContent.setRevisionNumber(metadata.get(Metadata.REVISION_NUMBER));
	  extractedContent.setSecurity(metadata.get(Metadata.SECURITY));
	  extractedContent.setSlideCount(metadata.get(Metadata.SLIDE_COUNT));
	  extractedContent.setTemplate(metadata.get(Metadata.TEMPLATE));
	  extractedContent.setTotalTime(metadata.get(Metadata.TOTAL_TIME));
	  extractedContent.setVersion(metadata.get(Metadata.VERSION));
	  extractedContent.setWordCount(metadata.get(Metadata.WORD_COUNT));
	  
	  // CLIMATEFORCAST
	  //extractedContent.setClimateForcastAcknowledgement(metadata.get(org.apache.tika.metadata.ClimateForcast.ACKNOWLEDGEMENT));	  
	  //extractedContent.setClimateForcastCommandLine(metadata.get(org.apache.tika.metadata.ClimateForcast.COMMAND_LINE));	  
	  //extractedContent.setClimateForcastComment(metadata.get(org.apache.tika.metadata.ClimateForcast.COMMENT));	  
	  //extractedContent.setClimateForcastContact(metadata.get(org.apache.tika.metadata.ClimateForcast.CONTACT));	  
	  //extractedContent.setClimateForcastConvention(metadata.get(org.apache.tika.metadata.ClimateForcast.CONVENTIONS));	  
	  //extractedContent.setClimateForcastExperimentID(metadata.get(org.apache.tika.metadata.ClimateForcast.EXPERIMENT_ID));	  
	  //extractedContent.setClimateForcastHistory(metadata.get(org.apache.tika.metadata.ClimateForcast.HISTORY));	  
	  //extractedContent.setClimateForcastInstitution(metadata.get(org.apache.tika.metadata.ClimateForcast.INSTITUTION));	  
	  //extractedContent.setClimateForcastModelName(metadata.get(org.apache.tika.metadata.ClimateForcast.MODEL_NAME_ENGLISH));	  
	  //extractedContent.setClimateForcastProgramID(metadata.get(org.apache.tika.metadata.ClimateForcast.PROGRAM_ID));	  
	  //extractedContent.setClimateForcastProjectID(metadata.get(org.apache.tika.metadata.ClimateForcast.PROJECT_ID));	  
	  //extractedContent.setClimateForcastRealization(metadata.get(org.apache.tika.metadata.ClimateForcast.REALIZATION));	  
	  //extractedContent.setClimateForcastReferences(metadata.get(org.apache.tika.metadata.ClimateForcast.REFERENCES));	  
	  //extractedContent.setClimateForcastSource(metadata.get(org.apache.tika.metadata.ClimateForcast.SOURCE));	  
	  //extractedContent.setClimateForcastTableID(metadata.get(org.apache.tika.metadata.ClimateForcast.TABLE_ID));	  
	  
	  // TIFF
	  //extractedContent.setTIFFBitsPerSample(metadata.get(Metadata.BITS_PER_SAMPLE));
	  //extractedContent.setTIFFEquipmentMake(metadata.get(Metadata.EQUIPMENT_MAKE));
	  //extractedContent.setTIFFEquipmentModel(metadata.get(Metadata.EQUIPMENT_MODEL));
	  //extractedContent.setTIFFExposureLimit(metadata.get(Metadata.EXPOSURE_TIME));
	  //extractedContent.setTIFFFNumber(metadata.get(Metadata.F_NUMBER));
	  //extractedContent.setTIFFFlashFired(metadata.get(Metadata.FLASH_FIRED));
	  //extractedContent.setTIFFFocalLength(metadata.get(Metadata.FOCAL_LENGTH));
	  //extractedContent.setTIFFImageLength(metadata.get(Metadata.IMAGE_LENGTH));
	  //extractedContent.setTIFFImageWidth(metadata.get(Metadata.IMAGE_WIDTH));
	  //extractedContent.setTIFFISOSpeedRating(metadata.get(Metadata.ISO_SPEED_RATINGS));
	  //extractedContent.setTIFFOrientation(metadata.get(Metadata.ORIENTATION));
	  //extractedContent.setTIFFOriginalDate(metadata.get(Metadata.ORIGINAL_DATE));
	  //extractedContent.setTIFFResolutionHorizontal(metadata.get(Metadata.RESOLUTION_HORIZONTAL));
	  //extractedContent.setTIFFResolutionUnit(metadata.get(Metadata.RESOLUTION_UNIT));
	  //extractedContent.setTIFFResolutionVertical(metadata.get(Metadata.RESOLUTION_VERTICAL));
	  //extractedContent.setTIFFSamplePerPixel(metadata.get(Metadata.SAMPLES_PER_PIXEL));
	  //extractedContent.setTIFFSoftware(metadata.get(Metadata.SOFTWARE));
	  	  
	  // TIKA METADATA KEYS
	  extractedContent.setResourceNameKey(metadata.get(Metadata.RESOURCE_NAME_KEY));
	  
	  // TIKA MIME KEYS
	  extractedContent.setMimeTypeMagic(metadata.get(Metadata.MIME_TYPE_MAGIC));
	  extractedContent.setTikaMimeType(metadata.get(Metadata.TIKA_MIME_FILE));
  }
  
  //
  return extractedContent;
 }
 
 //
	private void parseAdapterConfiguration(String adapterConfigurationFile) throws Exception
	{
		//
		// Parse Configuration
		Document configurationDocument = saxBuilder.build(adapterConfigurationFile);

	}
}
