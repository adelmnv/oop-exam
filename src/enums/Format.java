package enums;

import research.BibtexFormatter;
import research.CitationFormatter;
import research.PlainTextFormatter;

public enum Format {
	 BIBTEX {
		 @Override
		 public CitationFormatter getFormatter() {
			 return new BibtexFormatter();
		 }
	 },
	 PLAIN_TEXT {
		 @Override
		 public CitationFormatter getFormatter() {
			 return new PlainTextFormatter();
		}
	 };

	public CitationFormatter getFormatter() {
        return new PlainTextFormatter();
    }
}
