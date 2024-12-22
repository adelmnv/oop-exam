package research;

import java.io.Serializable;

import enums.Format;
import utils.IdGenerator;

public class ResearchPaper implements Serializable{
	private static final long serialVersionUID = 1L;
	private String paperId;
    private String title;
    private Researcher author;
    private int publicationYear;
    private int numberOfPages;
    private int citationNumber;

    public ResearchPaper(String title, Researcher author, int publicationYear, int numberOfPages, int citationNumber) {
    	if (title == null || author == null) throw new IllegalArgumentException("Title and author must not be null.");
        if (publicationYear < 0 || numberOfPages <= 0) throw new IllegalArgumentException("Invalid year or page number.");
        this.paperId = IdGenerator.generateUniqueId("RP");
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.numberOfPages = numberOfPages;
        this.citationNumber = citationNumber;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Researcher getAuthor() {
        return author;
    }

    public void setAuthor(Researcher author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public int getCitationNumber() {
        return citationNumber;
    }

    public void setCitationNumber(int citationNumber) {
        this.citationNumber = citationNumber;
    }
    
    public void addCitation() {
        this.citationNumber++;
    }

    public String getCitation(Format format) {
    	CitationFormatter formatter = format.getFormatter();
        return formatter.format(this);
    }
    
    @Override
    public String toString() {
        return String.format("Research Paper ID: %s\nTitle: \"%s\"\nAuthor: %s\nYear: %d\nPages: %d\nCitations: %d\n",
                paperId, title, author.getResearcherName(), publicationYear, numberOfPages, citationNumber);
    }


    public static void main(String[] args) {
//    	StudentResearcher researcher = new StudentResearcher();
//        researcher.setName("Aibar");
//
//        ResearchPaper paper = new ResearchPaper(1, "Science research", researcher, 2024, 05, 13);
//
//        System.out.println("Bibtex citation:\n" + paper.getCitation(Format.BIBTEX));
//        System.out.println("\nPlain text citation:\n" + paper.getCitation(Format.PLAIN_TEXT));
    }
}