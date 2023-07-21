//Peter Alonzo: Final Project
public class NetflixMovie extends NetflixTitle                                      //Child class of NetflixTitle (NetflixMovie is a NetflixTitle)
{
    private int duration;                                                           //private int variable for movie duration (minutes)
    private String rating;                                                          //private String variable for movie rating
    public NetflixMovie(String idNum, String name, int yearReleased, String movieRating, int movieDuration, String [] allDirectors, String [] allCountries, String [] allGenres)
    {
        super(idNum, name, yearReleased, allDirectors, allCountries, allGenres);    //initializes attributes shared with superclass' constructor
        duration = movieDuration;                                                   //initializes local duration variable
        rating = movieRating;                                                       //initializes local rating variable
    }
    public void setDuration(int newDuration) { duration = newDuration; }            //Mutator method for local duration variable
    public int getDuration() { return duration; }                                   //Accessor method for local duration variable
    public void setRating(String newRating) { rating = newRating; }                 //Mutator method for local rating variable
    public String getRating() { return rating; }                                    //Accessor method for local rating variable
    public void printMovieInfo()                                                    //Void method that prints all relevant info about NetflixMovie object
    {
        System.out.println("\nType: Movie");
        super.printTitleInfo();                                                     //Calls print method from superclass to print common attributes
        System.out.println("Duration: " + getDuration() + " minutes");              //Gets remaining attributes of object using their getter methods
        System.out.println("Movie Rating: " + getRating());
    }
}
