//Peter Alonzo: Final Project
public class NetflixTitle                                                               //Superclass for titles in video inventory
{
    private String id, name;                                                            //private String variables for title's id and name
    private String [] directors, countries, genres;                                     //private lists for directors, genres, and countries of type String
    private int releaseYear;                                                            //private int variable for release year
    public NetflixTitle(String idNum, String titleName, int yearReleased, String [] allDirectors, String [] allCountries, String [] allGenres)
    {
        id = idNum;                                                                     //initializes local id variable
        name = titleName;                                                               //initializes local title name variable
        releaseYear = yearReleased;                                                     //initializes local year of release variable
        directors = allDirectors.clone();                                               //Utilizes .clone() to populate local directors list
        countries = allCountries.clone();                                               //Utilizes .clone() to populate local countries list
        genres = allGenres.clone();                                                     //utilizes .clone() to populate local genres list
    }
    public void setId(String newId) { id = newId; }                                     //Mutator method for local id variable
    public String getId() { return id; }                                                //Accessor method for local id variable
    public void setTitleName(String newName) { name = newName; }                        //Mutator method for local name variable
    public String getName() { return name; }                                            //Accessor method for local name variable
    public void setReleaseYear(int newYear) { releaseYear = newYear; }                  //Mutator method for local releaseYear variable
    public int getReleaseYear() { return releaseYear; }                                 //Accessor method for local releaseYear variable

    public void setDirectors(String [] newDirectors) { directors = newDirectors.clone(); } //Mutator method for local directors list
    public String [] allDirectors() { return directors; }                               //Returns non-formatted list of directors
    public String [] allCountries() { return countries; }                               //Returns non-formatted list of countries
    public String [] allGenres() { return genres; }                                     //Returns non-formatted list of genres
    public String getDirectors()                                                        //Accessor method for local directors list
    {
        String returnVal = "";
        if (directors.length == 0)                                                      //Checks if directors list is empty before iterating over it
        {
            return "No directors listed for this title";                                //Returns a message to the user indicating no listed directors
        }
        for (int i = 0; i < directors.length - 1; i++)
        {
            returnVal += directors[i] + ", ";                                           //For loop will list all directors in a comma-separated one-line String
        }
        returnVal += directors[directors.length - 1];                                   //Concatenates last element to string to avoid a random end comma
        return returnVal;                                                               //Returns the formatted string
    }
    public void setCountries(String [] newCountries) { countries = newCountries.clone(); } //Mutator method for local countries list
    public String getCountries()                                                        //Accessor method for local countries list
    {
        String returnVal = "";
        if (countries.length == 0)                                                      //Checks if countries list is empty before iterating over it
        {
            return "No countries listed for this title";
        }
        for (int i = 0; i < countries.length - 1; i++)                                  //For loop will list all countries in a comma-separated one-line String
        {
            returnVal += countries[i] + ", ";
        }
        returnVal += countries[countries.length - 1];                                   //Concatenates last element to string to avoid a random end comma
        return returnVal;                                                               //Returns the formatted string
    }
    public void setGenres(String [] newGenres) { genres = newGenres.clone(); }          //Mutator method for local genres list
    public String getGenres()                                                           //Accessor method for local genres list
    {
        String returnVal = "";
        if (genres.length == 0)                                                         //Checks if genres list is empty before iterating over it
        {
            return "No genres listed for this title";
        }
        for (int i = 0; i < genres.length - 1; i++)                                     //For loop will list all genres in a comma-separated one-line String
        {
            returnVal += genres[i] + ", ";
        }
        returnVal += genres[genres.length - 1];                                         //For loop will list all genres in a comma-separated one-line String
        return returnVal;                                                               //Returns the formatted string
    }
    public void printTitleInfo()                                                        //Void method that prints all relevant info about NetflixTitle object
    {
        System.out.println("Title Name: " + getName());                                 //Gets all values of object using their getter methods
        System.out.println("ID: " + getId());
        System.out.println("Directors: " + getDirectors());
        System.out.println("Countries: " + getCountries());
        System.out.println("Genres: " + getGenres());
        System.out.println("Release Year: " + getReleaseYear());
    }
}
