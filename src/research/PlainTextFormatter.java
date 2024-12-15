package research;

public class PlainTextFormatter implements CitationFormatter {
    @Override
    public String format(ResearchPaper paper) {
        return String.format("\"%s\" (ID: %d) by %s in %d. %d pages. Citation Number: %d.",
                paper.getTitle(), paper.getPaperId(), paper.getAuthor().getResearcherName(), paper.getPublicationYear(), paper.getNumberOfPages(), paper.getCitationNumber());
    }
}
