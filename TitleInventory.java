//Peter Alonzo: Final Project
import java.io.FileWriter;                                                                                  //Imported to update input file
import java.io.IOException;                                                                                 //Imported to handle errors with FileWriter
import java.util.ArrayList;                                                                                 //Will be utilized to hold NetflixTitle objects
public class TitleInventory                                                                                 //Data Container used to store NetflixTitle objects
{
    private ArrayList<NetflixTitle> titles = new ArrayList<NetflixTitle>();                                 //private ArrayList to contain all of the NetflixTitle objects
    private int numTitles;                                                                                  //private int for number of titles in collection
    public TitleInventory(ArrayList<NetflixTitle> elements)                                                 //Constructor that takes in an existing ArrayList of NetflixTitle objects
    {
        for (int i = 0; i < elements.size(); i++)                                                           //Iterates through every NetflixTitle object
        {
            titles.add(elements.get(i));                                                                    //Adds object to private titles ArrayList
        }
        numTitles = titles.size();                                                                          //Updates size of the inventory accordingly
    }
    public TitleInventory()                                                                                 //Default constructor
    {
        numTitles = 0;                                                                                      //initializes size of the collection to 0
    }
    public int getNumTitles() { return numTitles; }                                                         //Accessor method for local numTitles variable
    public NetflixTitle getObject(int index)                                                                //Accessor method for getting objects by index
    {
        return titles.get(index);                                                                           //Returns object at that index
    }
    public void addTitle(NetflixTitle title)
    {
        titles.add(title);                                                                                  //Adds object to private titles ArrayList
        numTitles += 1;                                                                                     //Updates size of the inventory accordingly
    }
    public void displayDurations(String type)                                                               //Displays all possible values for durations based on object type (Movie or TV Show)
    {
        int lowBoundMovie = 1, maxMovie = 30, count = 1;                                                    //Initializes variables needed to properly display ranges for movies (30 minute intervals)
        int lowBoundShow = 1, maxShow = 2;                                                                  //Initializes variables needed to properly display ranges for shows (1-2 season intervals)
        System.out.print("\nPlease select one of the unique attributes");
        for (int i = 0; i < this.titles.size(); i++)                                                        //Loops through all objects
        {
            if (type.equals("Movie") && this.titles.get(i) instanceof NetflixMovie)                         //checks if element is a Movie and user wants a movie
            {
                NetflixMovie element = ((NetflixMovie)this.titles.get(i));                                  //Casts the object to gain access to NetflixMovie's getDuration() method
                while (element.getDuration() > (maxMovie - 30))                                             //Checks if current object's range hasn't been printed yet
                {
                    System.out.print("\n" + count + ": " + lowBoundMovie + "-" + maxMovie + " minutes");
                    lowBoundMovie += 30;                                                                    //Updates interval bounds for movies accordingly
                    maxMovie += 30;
                    count++;                                                                                //Increments counter for listing purposes
                }
            }
            if (type.equals("TV Show") && this.titles.get(i) instanceof NetflixTVShow)                      //checks if element is a TV Show and user wants a TV Show
            {
                NetflixTVShow element = ((NetflixTVShow)this.titles.get(i));                                //Casts the object to gain access to NetflixTVShow's getSeasons() method
                while (element.getSeasons() > (maxShow - 2))                                                //Checks if current object's range hasn't been printed yet
                {
                    System.out.print("\n" + count + ": " + lowBoundShow + "-" + maxShow + " seasons");
                    lowBoundShow += 2;                                                                      //Updates interval bounds for shows accordingly
                    maxShow += 2;
                    count++;                                                                                //Increments counter for listing purposes
                }
            }
        }
        System.out.print("\nEnter the number of your desired range here: ");
    }
    public ArrayList<NetflixTitle> searchDuration(int choice, String type)                                  //Finds all titles with the given duration and object type (Movie or TV Show)
    {
        ArrayList<NetflixTitle> sublist = new ArrayList<NetflixTitle>();                                    //Stores all valid elements throughout execution and will be returned
        for (int i = 0; i < this.titles.size(); i++)                                                        //Loops through all objects
        {
            if (type.equals("Movie") && this.titles.get(i) instanceof NetflixMovie)                         //checks if element is a Movie and user wants a movie
            {
                int low = 30 * (choice - 1) + 1;                                                            //Initializes range for movie duration based on user's choice
                int high = 30 * choice;
                NetflixMovie element = ((NetflixMovie)this.titles.get(i));                                  //Casts the object to gain access to NetflixMovie's getDuration() method
                if (element.getDuration() >= low && element.getDuration() <= high)                          //Checks if duration falls in between the range
                {
                    sublist.add(this.titles.get(i));                                                        //Adds that particular title to sublist if prior statement is true
                }
            }
            if (type.equals("TV Show") && this.titles.get(i) instanceof NetflixTVShow)                      //checks if element is a TV Show and user wants a TV Show
            {
                int low = choice * 2 - 1;                                                                   //Initializes range for show duration based on user's choice
                int high = choice * 2;
                NetflixTVShow element = ((NetflixTVShow)this.titles.get(i));                                //Casts the object to gain access to NetflixTVShow's getSeasons() method
                if (element.getSeasons() >= low && element.getSeasons() <= high)                            //Checks if duration falls in between the range
                {
                    sublist.add(this.titles.get(i));                                                        //Adds that particular title to sublist if prior statement is true
                }
            }
        }
        return sublist;
    }
    public void displayRatings(String type)                                                                 //Displays all possible values for years based on object type (Movie or TV Show)
    {
        int count = 1;                                                                                      //Count used to list out elements
        ArrayList <String> ratings = new ArrayList<String>();                                               //Will be used to collect unique values as they are found
        System.out.print("\nPlease select one of the unique attributes");
        for (int i = 0; i < this.titles.size(); i++)                                                        //Loops through all objects
        {
            if (type.equals("Movie") && this.titles.get(i) instanceof NetflixMovie)                         //checks if element is a Movie and user wants a movie
            {
                NetflixMovie elem = ((NetflixMovie)this.titles.get(i));                                     //Casts the object to gain access to NetflixMovie's getRating() method
                if (!ratings.contains(elem.getRating()))                                                    //Checks if the rating is a unique value
                {
                    System.out.print("\n" + count + ": " + elem.getRating());                               //Prints it as an option for the user to choose
                    ratings.add(elem.getRating());                                                          //Adds that year to collection to avoid future repetition
                    count++;                                                                                //Increments counter for display purposes (shows as 1,2,3, etc.)
                }
            }
            if (type.equals("TV Show") && this.titles.get(i) instanceof NetflixTVShow)                      //checks if element is a TV Show and user wants a TV Show
            {
                NetflixTVShow elem = ((NetflixTVShow)this.titles.get(i));                                   //Casts the object to gain access to NetflixTVShow's getRating() method
                if (!ratings.contains(elem.getRating()))                                                    //Checks if the rating is a unique value
                {
                    System.out.print("\n" + count + ": " + elem.getRating());                               //Prints it as an option for the user to choose
                    ratings.add(elem.getRating());                                                          //Adds that year to collection to avoid future repetition
                    count++;                                                                                //Increments counter for display purposes (shows as 1,2,3, etc.)
                }
            }
        }
        System.out.print("\nEnter option here: ");
    }
    public ArrayList<NetflixTitle> searchRating(String rating, String type)                                 //Finds all titles with the given rating and object type (Movie or TV Show)
    {
        ArrayList<NetflixTitle> sublist = new ArrayList<NetflixTitle>();                                    //Stores all valid elements throughout execution and will be returned
        for (int i = 0; i < this.titles.size(); i++)                                                        //Loops through all objects
        {
            if (type.equals("Movie") && this.titles.get(i) instanceof NetflixMovie)                         //checks if element is a Movie and user wants a movie
            {
                NetflixMovie element = ((NetflixMovie) this.titles.get(i));                                 //Casts the object to gain access to NetflixMovie's getRating() method
                if (element.getRating().equalsIgnoreCase(rating))                                           //Adds to sublist if object has the correct rating
                {
                    sublist.add(this.titles.get(i));
                }
            }
            if (type.equals("TV Show") && this.titles.get(i) instanceof NetflixTVShow)                      //checks if element is a TV Show and user wants a TV Show
            {
                NetflixTVShow element = ((NetflixTVShow) this.titles.get(i));                               //Casts the object to gain access to NetflixTVShow's getRating() method
                if (element.getRating().equalsIgnoreCase(rating))                                           //Adds to sublist if object has the correct rating
                {
                    sublist.add(this.titles.get(i));
                }
            }
        }
        return sublist;                                                                                     //Returns sublist of elements with correct rating and object type (Movie or TV Show)
    }
    public void displayYears(String type)                                                                   //Displays all possible values for years based on object type (Movie or TV Show)
    {
        int count = 1;                                                                                      //Count used to list out elements
        ArrayList <Integer> years = new ArrayList<Integer>();                                               //Will be used to collect unique values as they are found
        System.out.print("\nPlease select one of the unique attributes");
        for (int i = 0; i < this.titles.size(); i++)                                                        //Loops through all objects
        {
            int year = this.titles.get(i).getReleaseYear();                                                 //Finds year of current element
            boolean movie = type.equals("Movie") && this.titles.get(i) instanceof NetflixMovie;             //checks if element is a Movie and user wants a movie
            boolean show = type.equals("TV Show") && this.titles.get(i) instanceof NetflixTVShow;           //checks if element is a TV Show and user wants a TV Show
            if (movie || show)                                                                              //Ensures we only continue if any of the prior conditions are true
            {
                if (!years.contains(year))                                                                  //Checks if the year is a unique value
                {
                    System.out.print("\n" + count + ": " + year);                                           //Prints it as an option for the user to choose
                    years.add(year);                                                                        //Adds that year to collection to avoid future repetition
                    count++;                                                                                //Increments counter for display purposes (shows as 1,2,3, etc.)
                }
            }
        }
        System.out.print("\nEnter option here: ");
    }
    public ArrayList<NetflixTitle> searchYear(int year, String type)                                        //Finds all titles with the given year and object type (Movie and TV Show)
    {
        ArrayList<NetflixTitle> sublist = new ArrayList<NetflixTitle>();                                    //Stores all valid elements throughout execution and will be returned
        for (int i = 0; i < this.titles.size(); i++)                                                        //Loops through all objects
        {
            if (year == this.titles.get(i).getReleaseYear())                                                //Verifies that the current object has the desired year
            {
                boolean movie = type.equals("Movie") && this.titles.get(i) instanceof NetflixMovie;         //checks if element is a Movie and user wants a movie
                boolean show = type.equals("TV Show") && this.titles.get(i) instanceof NetflixTVShow;       //checks if element is a TV Show and user wants a TV Show
                if (movie || show)                                                                          //Only adds it to sublist if either of the prior conditions is true
                {
                    sublist.add(this.titles.get(i));
                }
            }
        }
        return sublist;                                                                                     //Returns sublist of elements with correct year and object type (Movie or TV Show)
    }
    public void displayGenres(String type)                                                                  //Displays all possible values for genres based on object type (Movie or TV Show)
    {
        int count = 1;                                                                                      //Count used to list out elements
        ArrayList <String> genres = new ArrayList<String>();                                                //Will be used to collect unique values as they are found
        System.out.print("\nPlease select one of the unique attributes");
        for (int i = 0; i < this.titles.size(); i++)                                                        //Loops through all objects
        {
            String [] allGenres = this.titles.get(i).allGenres();                                           //Finds all genres associated with current title
            boolean movie = type.equals("Movie") && this.titles.get(i) instanceof NetflixMovie;             //checks if element is a Movie and user wants a movie
            boolean show = type.equals("TV Show") && this.titles.get(i) instanceof NetflixTVShow;           //checks if element is a TV Show and user wants a TV Show
            if (movie || show)                                                                              //Ensures we only continue if any of the prior conditions are true
            {
                for (int j = 0; j < allGenres.length; j++)                                                  //Iterates through all genres of current title
                {
                    if (!genres.contains(allGenres[j]))                                                     //Checks if genre is a unique value
                    {
                        System.out.print("\n" + count + ": " + allGenres[j]);                               //Prints it as an option for the user to choose
                        genres.add(allGenres[j]);                                                           //Adds that genre to collection to avoid future repetition
                        count++;                                                                            //Increments counter for display purposes (shows as 1,2,3, etc.)
                    }
                }
            }
        }
        System.out.print("\nEnter option here: ");
    }
    public ArrayList<NetflixTitle> searchGenre(String genre, String type)                                   //Finds all titles with the given genre and object type (Movie and TV Show)
    {
        ArrayList<NetflixTitle> sublist = new ArrayList<NetflixTitle>();                                    //Stores all valid elements throughout execution and will be returned
        for (int i = 0; i < this.titles.size(); i++)                                                        //Loops through all objects
        {
            boolean movie = type.equals("Movie") && this.titles.get(i) instanceof NetflixMovie;             //checks if element is a Movie and user wants a movie
            boolean show = type.equals("TV Show") && this.titles.get(i) instanceof NetflixTVShow;           //checks if element is a TV Show and user wants a TV Show
            if (movie || show)                                                                              //Only continues if either of the prior conditions is true
            {
                String [] genres = this.titles.get(i).allGenres();                                          //Gathers all genres for current element
                for (int j = 0; j < genres.length; j++)                                                     //Iterates through all genres of given element
                {
                    if (genre.equals(genres[j]))                                                            //If one of the genres of the element matches the user's genre
                    {
                        sublist.add(this.titles.get(i));                                                    //Adds it to the sublist and breaks
                        break;
                    }
                }
            }
        }
        return sublist;                                                                                     //Returns sublist of elements with correct genre and object type (Movie or TV Show)
    }
    public void displayDirectors(String type)                                                               //Displays all possible values for directors based on object type (Movie or TV Show)
    {
        int count = 1;                                                                                      //Count used to list out elements
        ArrayList <String> directors = new ArrayList<String>();                                             //Will be used to collect unique values as they are found
        System.out.print("\nPlease select one of the unique attributes");
        for (int i = 0; i < this.titles.size(); i++)                                                        //Loops through all objects
        {
            String [] allDirectors = this.titles.get(i).allDirectors();                                     //Finds all directors associated with current title
            boolean movie = type.equals("Movie") && this.titles.get(i) instanceof NetflixMovie;             //checks if element is a Movie and user wants a movie
            boolean show = type.equals("TV Show") && this.titles.get(i) instanceof NetflixTVShow;           //checks if element is a TV Show and user wants a TV Show
            if (movie || show)                                                                              //Ensures we only continue if any of the prior conditions are true
            {
                for (int j = 0; j < allDirectors.length; j++)                                               //Iterates through all directors of current title
                {
                    if (!directors.contains(allDirectors[j]) && !allDirectors[j].equals(""))                //Checks if director is a unique value and not empty
                    {
                        System.out.print("\n" + count + ": " + allDirectors[j]);                            //Prints it as an option for the user to choose
                        directors.add(allDirectors[j]);                                                     //Adds that director to collection to avoid future repetition
                        count++;                                                                            //Increments counter for display purposes (shows as 1,2,3, etc.)
                    }
                }
            }
        }
        System.out.print("\nEnter option here: ");
    }
    public ArrayList<NetflixTitle> searchDirector(String director, String type)                             //Finds all titles with the given director and object type (Movie and TV Show)
    {
        ArrayList<NetflixTitle> sublist = new ArrayList<NetflixTitle>();                                    //Stores all valid elements throughout execution and will be returned
        for (int i = 0; i < this.titles.size(); i++)                                                        //Loops through all objects
        {
            boolean movie = type.equals("Movie") && this.titles.get(i) instanceof NetflixMovie;             //checks if element is a Movie and user wants a movie
            boolean show = type.equals("TV Show") && this.titles.get(i) instanceof NetflixTVShow;           //checks if element is a TV Show and user wants a TV Show
            if (movie || show)                                                                              //Only continues if either of the prior conditions is true
            {
                String [] directors = this.titles.get(i).allDirectors();                                    //Gathers all directors for current element
                for (int j = 0; j < directors.length; j++)                                                  //Iterates through all directors of given element
                {
                    if (director.equals(directors[j]))                                                      //If one of the directors of the element matches the user's director
                    {
                        sublist.add(this.titles.get(i));                                                    //Adds it to the sublist and breaks
                        break;
                    }
                }
            }
        }
        return sublist;                                                                                     //Returns sublist of elements with correct director and object type (Movie or TV Show)
    }
    public void displayCountries(String type)                                                               //Displays all possible values for countries based on object type (Movie or TV Show)
    {
        int count = 1;                                                                                      //Count used to list out elements
        ArrayList <String> countries = new ArrayList<String>();                                             //Will be used to collect unique values as they are found
        System.out.print("\nPlease select one of the unique attributes");
        for (int i = 0; i < this.titles.size(); i++)                                                        //Loops through all objects
        {
            String [] allCountries = this.titles.get(i).allCountries();                                     //Finds all countries associated with current title
            boolean movie = type.equals("Movie") && this.titles.get(i) instanceof NetflixMovie;             //checks if element is a Movie and user wants a movie
            boolean show = type.equals("TV Show") && this.titles.get(i) instanceof NetflixTVShow;           //checks if element is a TV Show and user wants a TV Show
            if (movie || show)                                                                              //Ensures we only continue if any of the prior conditions are true
            {
                for (int j = 0; j < allCountries.length; j++)                                               //Iterates through all countries of current title
                {
                    if (!countries.contains(allCountries[j]) && !allCountries[j].equals(""))                //Checks if country is a unique value and not empty
                    {
                        System.out.print("\n" + count + ": " + allCountries[j]);                            //Prints it as an option for the user to choose
                        countries.add(allCountries[j]);                                                     //Adds that country to collection to avoid future repetition
                        count++;                                                                            //Increments counter for display purposes (shows as 1,2,3, etc.)
                    }
                }
            }
        }
        System.out.print("\nEnter option here: ");
    }
    public ArrayList<NetflixTitle> searchCountry(String country, String type)                               //Finds all titles with the given country and object type (Movie and TV Show)
    {
        ArrayList<NetflixTitle> sublist = new ArrayList<NetflixTitle>();                                    //Stores all valid elements throughout execution and will be returned
        for (int i = 0; i < this.titles.size(); i++)                                                        //Loops through all objects
        {
            boolean movie = type.equals("Movie") && this.titles.get(i) instanceof NetflixMovie;             //checks if element is a Movie and user wants a movie
            boolean show = type.equals("TV Show") && this.titles.get(i) instanceof NetflixTVShow;           //checks if element is a TV Show and user wants a TV Show
            if (movie || show)                                                                              //Only continues if either of the prior conditions is true
            {
                String [] countries = this.titles.get(i).allCountries();                                    //Gathers all countries for current element
                for (int j = 0; j < countries.length; j++)                                                  //Iterates through all countries of given element
                {
                    if (country.equals(countries[j]))                                                       //If one of the countries of the element matches the user's country
                    {
                        sublist.add(this.titles.get(i));                                                    //Adds it to the sublist and breaks
                        break;
                    }
                }
            }
        }
        return sublist;                                                                                     //Returns sublist of elements with correct country and object type (Movie or TV Show)
    }
    public String titleFormat(NetflixTitle object)                                                          //Returns string of information based on object
    {
        String val = "";
        val += object.getId() + ",";                                                                        //Appends id to returning string
        String type;
        if (object instanceof NetflixMovie) { val += "Movie,"; }                                            //Adds type based on the object type
        else { val += "TV Show,"; }
        String name = object.getName();                                                                     //Appends name and adds quote if a comma is included
        String directors = object.getDirectors();
        if (object.allDirectors().length > 1) { directors = "\"" + directors + "\""; }                      //Properly adds directors to string as a list
        String countries = object.getCountries();
        if (object.allCountries().length > 1) { countries = "\"" + countries + "\""; }                      //Properly adds countries to string as a list
        val += name + "," + directors + "," + countries + ",";
        val += String.valueOf(object.getReleaseYear()) + ",";                                               //Casts year to string and adds it to val
        if (object instanceof NetflixMovie) { val += ((NetflixMovie) object).getRating() + ","; }           //Adds rating based on object type
        else { val += ((NetflixTVShow) object).getRating() + ","; }
        if (object instanceof NetflixMovie)
        {
            val += String.valueOf(((NetflixMovie)object).getDuration()) + " min,";                          //If object is a Movie, add duration with minutes
        }
        else
        {
            if (((NetflixTVShow) object).getSeasons() == 1)
            {
                val += String.valueOf(((NetflixTVShow) object).getSeasons()) + " Season,";                  //If TV Show is 1 season, append Season to the duration
            }
            else
            {
                val += String.valueOf(((NetflixTVShow) object).getSeasons()) + " Seasons,";                 //Else, append Seasons to duration and add it to val
            }
        }
        String genres = object.getGenres();                                                                 //Properly adds genres to String as list
        if (object.allGenres().length > 1) { genres = "\"" + genres + "\""; }
        val += genres;
        return val;                                                                                         //returns completed string of object's attributes
    }
    public ArrayList<NetflixTitle> sortedTitles()                                                           //Will make an Arraylist with Movies first, followed by TV Shows
    {
        ArrayList<NetflixTitle> temp = new ArrayList<NetflixTitle>();                                       //ArrayList for return
        for (int i = 0; i < this.getNumTitles(); i++)
        {
            if (this.titles.get(i) instanceof NetflixMovie)                                                 //Checks if current object in loop is a Movie
            {
                temp.add(this.titles.get(i));                                                               //Adds all NetflixMovie objects to temp first
            }
        }
        for (int j = 0; j < this.getNumTitles(); j++)
        {
            if (this.titles.get(j) instanceof NetflixTVShow)                                                //Checks if current object in loop is a TV Show
            {
                temp.add(this.titles.get(j));                                                               //Adds all NetflixTVShow objects to temp after (will be Movies first, TV Shows after)
            }
        }
        return temp;                                                                                        //Returns "sorted" ArrayList based on private titles collection
    }
    public int showTitles(int first, int last, int status)                                                  //Will be used to display the titles of an inventory in groups of 10, if possible
    {
        ArrayList<NetflixTitle> temp = this.sortedTitles();                                                 //Makes sorted ArrayList for proper display
        if (status == 1)                                                                                    //Status of 1 indicates no more unique titles are available for display
        {
            System.out.print("No more titles to display. Please enter a number (1-" + temp.size() + "): "); //Alerts user to pick one of the already displayed options
            return 1;                                                                                       //Returns a status of 1
        }
        else if (temp.size() <= last * 10)                                                                  //Checks if this group will contain the last elements in the ArrayList
        {
            for (int i = first * 10; i < temp.size(); i++)                                                  //Runs from current element to last element in the ArrayList
            {
                System.out.print("\n" + (i + 1) + ": " + temp.get(i).getName());                            //Prints objects with their given index and title
            }
            return 1;                                                                                       //Will return a 1 status to tester class
        }
        else
        {
            for (int j = first * 10; j < last * 10; j++)                                                    //Will print 10 new objects for user to see
            {
                System.out.print("\n" + (j + 1) + ": " + temp.get(j).getName());                            //Prints objects with their given index and title
            }
            return 0;                                                                                       //Status of 0 indicates more titles can be printed if needed
        }
    }
    public void deleteTitle(int index)                                                                      //Deletes object given index of object in "sorted" ArrayList
    {
        ArrayList<NetflixTitle> temp = this.sortedTitles();                                                 //Makes same ArrayList as used in showTitles()
        this.titles.remove(temp.get(index - 1));                                                            //Removes object with given index in temp array from inventory's private titles collection
        System.out.print("Title: " + temp.get(index - 1).getName() + " will be deleted now\n");             //Indicates to user which title is being deleted
        numTitles--;                                                                                        //Updates numTitles variable after deletion
    }
    public void updateFile(String name)                                                                     //Method used to update the input file
    {
        try                                                                                                 //Try block used to update input file after deletion
        {
            FileWriter output = new FileWriter(name);                                                       //Opens the input file given in beginning
            output.write("show_id,type,title,director,country,release_year,rating,duration,Genre\n");       //Adds the header on the top of file
            for(int i = 0; i < this.getNumTitles() - 1; i++)                                                //Goes through n - 1 objects, where n is the number of objects in inventory
            {
                NetflixTitle object = this.getObject(i);                                                    //Gets particular object based on i
                output.write(this.titleFormat(object) + "\n");                                              //Formats object, adds newline, and writes it to file
            }
            NetflixTitle object = this.getObject(this.getNumTitles() - 1);                                  //Gets the last object in the inventory
            output.write(this.titleFormat(object));                                                         //Formats object and writes it to file
            output.close();                                                                                 //Closes file after writing is done
        }
        catch (IOException e)                                                                               //Catches error with opening/writing to file
        {
            System.out.println("An error occurred :(");                                                     //Tells user there is an error
        }
    }
    public String changeRating(int index, String newRating)                                                 //Will fix the rating of a particular title, returns the name of the title
    {
        ArrayList<NetflixTitle> temp = this.sortedTitles();                                                 //Makes same ArrayList as used in showTitles()
        String name = temp.get(index - 1).getName();                                                        //Gets the name of the title needing to be modified
        for (int i = 0; i < this.titles.size(); i++)
        {
            if(this.titles.get(i).getName().equals(name) && this.titles.get(i) instanceof NetflixMovie)     //Checks if the title is the correct title and if it is a Movie
            {
                ((NetflixMovie) this.titles.get(i)).setRating(newRating);                                   //Sets new rating of movie and breaks out of loop
                break;
            }
            if(this.titles.get(i).getName().equals(name) && this.titles.get(i) instanceof NetflixTVShow)    //Checks if the title is the correct title and if it is a TV Show
            {
                ((NetflixTVShow) this.titles.get(i)).setRating(newRating);                                  //Sets new rating of tv show and breaks out of loop
                break;
            }
        }
        return name;                                                                                        //returns name of modified title
    }
    public void printTitles()                                                                               //Takes all elements in the TitleInventory and prints out their titles
    {
        System.out.print("\nTitles Found:\n");
        for (int i = 0; i < this.titles.size(); i++)                                                        //Iterates over every element
        {
            System.out.print("" + (i + 1) + ".) " + this.titles.get(i).getName() + "\n");                   //Prints their name with a counter
        }
        if (this.titles.size() == 0)                                                                        //Tests if there are no titles in this particular inventory
        {
            System.out.print("No titles found :(\n");                                                       //Alerts user that there are no titles found
        }
    }
}
