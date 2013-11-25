package com.thetdgroup.extraction_utils;

import org.json.JSONException;
import org.json.JSONObject;

public class ContentInformation
{
 private String importedFileName = "";
 private String applicationName = "";
 private String applicationVersion = "";
 private String category = "";
 private String title = "";
 private String author = "";
 private String contentData = "";
 private String characterCount = "";
 private String characterCountWithSpace = "";
 private String comments = "";
 private String company = "";
 private String contentDisposition = "";
 private String contentEncoding = "";
 private String contentLanguage = "";
 private String contentLength = "";
 private String contentLocation = "";
 private String contentMD5 = "";
 private String contentStatus = "";
 private String contentType = "application/octet-stream";
 private String contributor = "";
 private String coverage = "";
 private String creationDate = "";
 private String creator = "";
 private String date = "";
 private String description = "";
	private String editTime = "";
	private String format = "";
	private String identifier = "";
	private String keywords = "";
	private String language = "";
	private String lastAuthor = "";
	private String lastModifier = "";
	private String lastPrinted = "";
	private String lastSaved = "";
	private String licenseLocation = "";
	private String licenceURL = "";
	private String lineCount = "";
	private String location = "";
	private String manager = "";
	private String mimeTypeMagic = "";
	private String modified = "";
	private String notes = "";
	private String pageCount = "";
	private String paragraphCount = "";
	private String presentationFormat = "";
	private String isProtected = "";
	private String publisher = "";
	private String relation = "";
	private String resourceNameKey = "";
	private String revisionNumber = "";
	private String rights = "";
	private String security = "";
	private String dublinSource = "";
	private String climateSource = "";
 private String slideCount = "";	
	private String subject = "";
	private String template = "";
	private String tikaMimeType = "";
	private String totalTime = "";
	private String type = "";
	private String version = "";
	private String wordCount = "";
	private String workType = "";
	
	private boolean hasException = false;
	private String exception = "";
	
 //
	public String getImportedFileName()
	{
		return importedFileName;
	}

	public void setImportedFileName(String importedFileName)
	{
		this.importedFileName = importedFileName;
	}
	
	public String getApplicationName()
	{
		return applicationName;
	}
	
	public void setApplicationName(String applicationName)
	{
		this.applicationName = applicationName;
	}
	
	public String getApplicationVersion()
	{
		return applicationVersion;
	}
	
	public void setApplicationVersion(String applicationVersion)
	{
		this.applicationVersion = applicationVersion;
	}
	
	public String getCategory()
	{
		return category;
	}
	
	public void setCategory(String category)
	{
		this.category = category;
	}
	
	public String getContentType()
	{
		return contentType;
	}
	
	public void setContentType(String contentType)
	{
		this.contentType = contentType;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public String getAuthor()
	{
		return author;
	}
	
	public void setAuthor(String author)
	{
		this.author = author;
	}
	
	public String getWordCount()
	{
		return wordCount;
	}
	
	public void setWordCount(String wordCount)
	{
		this.wordCount = wordCount;
	}
	
	public String getContentData()
	{
		return contentData;
	}
	
	public void setContentData(String contentData)
	{
		this.contentData = contentData;
	}
	
	public String getCharacterCount()
	{
		return characterCount;
	}

	public void setCharacterCount(String characterCount)
	{
		this.characterCount = characterCount;
	}

	public String getCharacterCountWithSpace()
	{
		return characterCountWithSpace;
	}

	public void setCharacterCountWithSpace(String characterCountWithSpace)
	{
		this.characterCountWithSpace = characterCountWithSpace;
	}

	public String getComments()
	{
		return comments;
	}

	public void setComments(String comments)
	{
		this.comments = comments;
	}

	public String getCompany()
	{
		return company;
	}

	public void setCompany(String company)
	{
		this.company = company;
	}

	public String getContentDisposition()
	{
		return contentDisposition;
	}

	public void setContentDisposition(String contentDisposition)
	{
		this.contentDisposition = contentDisposition;
	}

	public String getContentEncoding()
	{
		return contentEncoding;
	}

	public void setContentEncoding(String contentEncoding)
	{
		this.contentEncoding = contentEncoding;
	}

	public String getContentLanguage()
	{
		return contentLanguage;
	}

	public void setContentLanguage(String contentLanguage)
	{
		this.contentLanguage = contentLanguage;
	}

	public String getContentLength()
	{
		return contentLength;
	}

	public void setContentLength(String contentLength)
	{
		this.contentLength = contentLength;
	}

	public String getContentLocation()
	{
		return contentLocation;
	}

	public void setContentLocation(String contentLocation)
	{
		this.contentLocation = contentLocation;
	}

	public String getContentMD5()
	{
		return contentMD5;
	}

	public void setContentMD5(String contentMD5)
	{
		this.contentMD5 = contentMD5;
	}

	public String getContentStatus()
	{
		return contentStatus;
	}

	public void setContentStatus(String contentStatus)
	{
		this.contentStatus = contentStatus;
	}

	public String getContributor()
	{
		return contributor;
	}

	public void setContributor(String contributor)
	{
		this.contributor = contributor;
	}

	public String getCoverage()
	{
		return coverage;
	}

	public void setCoverage(String coverage)
	{
		this.coverage = coverage;
	}

	public String getCreationDate()
	{
		return creationDate;
	}

	public void setCreationDate(String creationDate)
	{
		this.creationDate = creationDate;
	}

	public String getCreator()
	{
		return creator;
	}

	public void setCreator(String creator)
	{
		this.creator = creator;
	}

	public String getDate()
	{
		return date;
	}

	public void setDate(String date)
	{
		this.date = date;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getEditTime()
	{
		return editTime;
	}

	public void setEditTime(String editTime)
	{
		this.editTime = editTime;
	}

	public String getFormat()
	{
		return format;
	}

	public void setFormat(String format)
	{
		this.format = format;
	}

	public String getIdentifier()
	{
		return identifier;
	}

	public void setIdentifier(String identifier)
	{
		this.identifier = identifier;
	}

	public String getKeywords()
	{
		return keywords;
	}

	public void setKeywords(String keywords)
	{
		this.keywords = keywords;
	}

	public String getLanguage()
	{
		return language;
	}

	public void setLanguage(String language)
	{
		this.language = language;
	}

	public String getLastAuthor()
	{
		return lastAuthor;
	}

	public void setLastAuthor(String lastAuthor)
	{
		this.lastAuthor = lastAuthor;
	}

	public String getLastModifier()
	{
		return lastModifier;
	}

	public void setLastModifier(String lastModifier)
	{
		this.lastModifier = lastModifier;
	}

	public String getLastPrinted()
	{
		return lastPrinted;
	}

	public void setLastPrinted(String lastPrinted)
	{
		this.lastPrinted = lastPrinted;
	}

	public String getLastSaved()
	{
		return lastSaved;
	}

	public void setLastSaved(String lastSaved)
	{
		this.lastSaved = lastSaved;
	}

	public String getLicenseLocation()
	{
		return licenseLocation;
	}

	public void setLicenseLocation(String licenseLocation)
	{
		this.licenseLocation = licenseLocation;
	}

	public String getLicenceURL()
	{
		return licenceURL;
	}

	public void setLicenceURL(String licenceURL)
	{
		this.licenceURL = licenceURL;
	}

	public String getLineCount()
	{
		return lineCount;
	}

	public void setLineCount(String lineCount)
	{
		this.lineCount = lineCount;
	}

	public String getLocation()
	{
		return location;
	}

	public void setLocation(String location)
	{
		this.location = location;
	}

	public String getManager()
	{
		return manager;
	}

	public void setManager(String manager)
	{
		this.manager = manager;
	}

	public String getMimeTypeMagic()
	{
		return mimeTypeMagic;
	}

	public void setMimeTypeMagic(String mimeTypeMagic)
	{
		this.mimeTypeMagic = mimeTypeMagic;
	}

	public String getModified()
	{
		return modified;
	}

	public void setModified(String modified)
	{
		this.modified = modified;
	}

	public String getNotes()
	{
		return notes;
	}

	public void setNotes(String notes)
	{
		this.notes = notes;
	}

	public String getPageCount()
	{
		return pageCount;
	}

	public void setPageCount(String pageCount)
	{
		this.pageCount = pageCount;
	}

	public String getParagraphCount()
	{
		return paragraphCount;
	}

	public void setParagraphCount(String paragraphCount)
	{
		this.paragraphCount = paragraphCount;
	}

	public String getPresentationFormat()
	{
		return presentationFormat;
	}

	public void setPresentationFormat(String presentationFormat)
	{
		this.presentationFormat = presentationFormat;
	}

	public String getIsProtected()
	{
		return isProtected;
	}

	public void setIsProtected(String isProtected)
	{
		this.isProtected = isProtected;
	}

	public String getPublisher()
	{
		return publisher;
	}

	public void setPublisher(String publisher)
	{
		this.publisher = publisher;
	}

	public String getRelation()
	{
		return relation;
	}

	public void setRelation(String relation)
	{
		this.relation = relation;
	}

	public String getResourceNameKey()
	{
		return resourceNameKey;
	}

	public void setResourceNameKey(String resourceNameKey)
	{
		this.resourceNameKey = resourceNameKey;
	}

	public String getRevisionNumber()
	{
		return revisionNumber;
	}

	public void setRevisionNumber(String revisionNumber)
	{
		this.revisionNumber = revisionNumber;
	}

	public String getRights()
	{
		return rights;
	}

	public void setRights(String rights)
	{
		this.rights = rights;
	}

	public String getSecurity()
	{
		return security;
	}

	public void setSecurity(String security)
	{
		this.security = security;
	}

	public String getDublinSource()
	{
		return dublinSource;
	}

	public void setDublinSource(String dublinSource)
	{
		this.dublinSource = dublinSource;
	}
	
	public String getClimateForcastSource()
	{
		return climateSource;
	}

	public void setClimateForcastSource(String climateSource)
	{
		this.climateSource = climateSource;
	}
	
	public String getSlideCount()
	{
		return slideCount;
	}

	public void setSlideCount(String slideCount)
	{
		this.slideCount = slideCount;
	}

	public String getSubject()
	{
		return subject;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	public String getTemplate()
	{
		return template;
	}

	public void setTemplate(String template)
	{
		this.template = template;
	}

	public String getTikaMimeType()
	{
		return tikaMimeType;
	}

	public void setTikaMimeType(String tikaMimeType)
	{
		this.tikaMimeType = tikaMimeType;
	}

	public String getTotalTime()
	{
		return totalTime;
	}

	public void setTotalTime(String totalTime)
	{
		this.totalTime = totalTime;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getVersion()
	{
		return version;
	}

	public void setVersion(String version)
	{
		this.version = version;
	}

	public String getWorkType()
	{
		return workType;
	}

	public void setWorkType(String workType)
	{
		this.workType = workType;
	}
	
	public boolean isHasException()
	{
		return hasException;
	}

	public void hasException()
	{
		this.hasException = true;
	}

	public String getException()
	{
		return exception;
	}

	public void setException(String exception)
	{
		this.exception = exception;
	}

	//
	public JSONObject toJSON() throws JSONException
	{
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("imported_file_name", importedFileName);
		jsonObject.put("application_name", applicationName);
		jsonObject.put("application_version", applicationVersion);
		jsonObject.put("author", author);
		jsonObject.put("character_count", characterCount);
		jsonObject.put("character_count_with_whitespace", characterCountWithSpace);
		jsonObject.put("comments", comments);
		jsonObject.put("company", company);
		jsonObject.put("content_disposition", contentDisposition);
		jsonObject.put("content_encoding", contentEncoding);
		jsonObject.put("content_language", contentLanguage);
		jsonObject.put("content_length", contentLength);
		jsonObject.put("content_location", contentLocation);
		jsonObject.put("content_md5", contentMD5);
		jsonObject.put("content_status", contentStatus);
		jsonObject.put("content_type", contentType);
		jsonObject.put("contributor", contributor);
		jsonObject.put("coverage", coverage);
		jsonObject.put("creation_date", creationDate);
		jsonObject.put("creator", creator);
		jsonObject.put("date", date);
		jsonObject.put("description", description);
		jsonObject.put("edit_time",  editTime);
		jsonObject.put("format", format);
		jsonObject.put("identifier", identifier);
		jsonObject.put("keywords", keywords);
		jsonObject.put("language", language);
		jsonObject.put("last_author", lastAuthor);
		jsonObject.put("last_modifier", lastModifier);
		jsonObject.put("last_printed", lastPrinted);
		jsonObject.put("last_saved", lastSaved);
		jsonObject.put("license_location", licenseLocation);
		jsonObject.put("license_url", licenceURL);
		jsonObject.put("line_count", lineCount);
		jsonObject.put("location", location);
		jsonObject.put("manager", manager);
		jsonObject.put("mimetype_magic", mimeTypeMagic);
		jsonObject.put("modified", modified);
		jsonObject.put("notes", notes);
		jsonObject.put("page_count", pageCount);
		jsonObject.put("paragraph_count", paragraphCount);
		jsonObject.put("presentation_format", presentationFormat);
		jsonObject.put("is_protected", isProtected);
		jsonObject.put("publisher", publisher);
		jsonObject.put("relation", relation);
		jsonObject.put("resource_name_key", resourceNameKey);
		jsonObject.put("revision_number", revisionNumber);
		jsonObject.put("rights", rights);
		jsonObject.put("security", security);
		jsonObject.put("slide_count", slideCount);
		jsonObject.put("source", dublinSource);
		jsonObject.put("subject", subject);
		jsonObject.put("template", template);
		jsonObject.put("tika_mimetype", tikaMimeType);
		jsonObject.put("total_time", totalTime);
		jsonObject.put("type", type);
		jsonObject.put("version", version);
		jsonObject.put("word_count", wordCount);
		jsonObject.put("work_type", workType);
		
		//
		if(hasException == true)
		{
			jsonObject.put("has_exception", true);
			jsonObject.put("exception", exception);
		}
		
		//
		return jsonObject;
	}
}
