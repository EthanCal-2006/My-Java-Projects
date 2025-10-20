package personcompare;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Person person1 = new Person("Justine", "Reha");
        Person person2 = new Person("Briana", "Reha");
        Person person3 = new Person(person1);

        if (person1 == person2)
            System.out.println("These persons are identical using ==");
        else
            System.out.println("These persons are not identical using ==");
        

        if (person1.equals(person2)) 
            System.out.println("These persons are equal using .equals()");
        else
            System.out.println("These persons are not equal using .equals()");
        

        if (person1.equals(person3))
            System.out.println("These copied persons are identical using .equals()");
         else
            System.out.println("These copied persons are not identical using .equals()");
        

        System.out.println(person1);
        System.out.println(person2.toString());
        System.out.println(person3);


    }
}
