package research;

public class CanBeResearcher {
    private ResearcherClass researcher;

    public CanBeResearcher() {
        this.researcher = null;
    }

    public ResearcherClass getResearcher() {
        return researcher;
    }

    public void setResearcher(ResearcherClass researcher) {
        this.researcher = researcher;
    }

    public void canBeResearcher() {
        if (researcher != null) {
            int hIndex = researcher.calculateHIndex();
            if (hIndex >= 3) {
            	System.out.print(hIndex);
            	System.out.println(" ");
                System.out.println("This person can be a researcher.");
            } else {
            	System.out.print(hIndex);
            	System.out.println(" ");
                throw new InvalidHIndexException("H-index is less than 3. Cannot be a researcher.");
            }
        } else {
        	
            System.out.println("This person is not a researcher.");
        }
    }

}
