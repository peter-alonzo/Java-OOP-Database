//Peter Alonzo: Final Project
public class NetflixTVShow extends NetflixTitle                                     //Child class of NetflixTitle (NetflixTVShow is a NetflixTitle)
{
    private int seasons;                                                            //private int variable for show duration (seasons)
    private String rating;                                                          //private String variable for movie rating
    public NetflixTVShow(String idNum, String name, int yearReleased, String TVRating, int TVSeasons, String [] allDirectors, String [] allCountries, String [] allGenres)
    {
        super(idNum, name, yearReleased, allDirectors, allCountries, allGenres);    //initializes attributes shared with superclass' constructor
        seasons = TVSeasons;                                                        //initializes local seasons variable
        rating = TVRating;                                                          //initializes local rating variable
    }
    public void setSeasons(int newSeasons) { seasons = newSeasons; }                //Mutator method for local seasons variable
    public int getSeasons() { return seasons; }                                     //Accessor method for local seasons variable
    public void setRating(String newRating) { rating = newRating; }                 //Mutator method for local rating variable
    public String getRating() { return rating; }                                    //Accessor method for local rating variable
    public void printTVInfo()                                                       //Void method that prints all relevant info about NetflixTVShow object
    {
        System.out.println("\nType: TV Show");
        super.printTitleInfo();                                                     //Calls print method from superclass to print common attributes
        System.out.println("Duration: " + getSeasons() + " seasons");               //Gets remaining attributes of object using their getter methods
        System.out.println("TV Rating: " + getRating());
    }
}
