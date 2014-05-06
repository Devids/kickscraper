# Kickscraper

Kickscraper is a tool to extract project data from Kickstarter.

## How it works

This version now uses Kickstarter's AJAX requests (JSON) and html parsing.
HTML parsing is optional, and slow.
Data extracting with only the ajax request are fast.
The data exported into csv file.
The extracted data:
name, blurb, goal, pledged, state, slug, country, currency, deadline, created_at, launched_at, state_changed_at, creator_name, creator_url, location_name, location_country, location_state, category, url, website, pledges

Website, plegdes and backers available only with the html parser.

Uses Gson for JSON parsing, Jsoup for html parsing and OpenCSV for csv wirting.

It was fully functional at 17th March.
Future changes in Kickstarter html code, or JSON data structure can cause that the app not working any more.

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

This extract 100 project, from category technology (16), sorted by "most_funded", the html parser is enabled and the app use 4 thread.

## Used 3rd party libs:
OpenCSV: http://opencsv.sourceforge.net/
GSON: http://code.google.com/p/google-gson/
JSOUP: http://jsoup.org/