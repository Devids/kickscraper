# Kickscraper

Kickscraper is a tool to automatically extract project related information (for example project goal, total number of backers, etc.) from Kickstarter.

## How it works

This version of the tool uses Kickstarter's AJAX requests (JSON) and html parsing. 
HTML parsing is optional, and slow, while data extracting with only the ajax requests are fast. 

The data is exported into a csv file, with the following columns: 

name, blurb, goal, pledged, state, slug, country, currency, deadline, created_at, launched_at, state_changed_at, creator_name, creator_url, location_name, location_country, location_state, category, url, website, pledges

Website, plegdes and backers available only with the html parser.

The tool uses Gson for JSON parsing, Jsoup for html parsing and OpenCSV for csv wirting.

The tool was used on 17th March, with full functionality. Future changes in Kickstarter html code, or JSON data structure could cause that, the application will not work properly.


## Usage

java -jar kickstarter-scraper-1.0-SNAPSHOT.jar sorting category_id threads html_parser_flag offset limit

Parameters:

sorting: Kickstarter supported sorting parameters. e.g.: most_funded, end_date, launch_date etc.

category_id: (Number) The id of the category, from the data will be extracted. e.g: Technology - 16, Music - 14 etc.

threads: (Number) How many threads do you want to use.

html_parser_flag: If you want to enable set to 1, otherwise 0.

offset: Project number offset. e.g: If you want to start from the 100th project set to 100.

limit: Number of projects you want to extract.


## Sample Usage

java -jar kickstarter-scraper-1.0-SNAPSHOT.jar most_funded 16 4 1 0 100

This extract 100 project, from category technology (16), sorted by "most_funded", the html parser is enabled and using 4 threads.

## Used 3rd party libs:

OpenCSV: http://opencsv.sourceforge.net/

GSON: http://code.google.com/p/google-gson/

JSOUP: http://jsoup.org/