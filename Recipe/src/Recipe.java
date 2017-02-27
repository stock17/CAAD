/**
 * Created by Yury on 22.02.2017.
 */


import java.io.*;
import java.util.*;

public class Recipe {

    private String name;
    private List<String> ingredients;
    private List<String> steps;

    public static Recipe favourite = null;

    public static void saveFavouriteToFile() throws  IOException{

        ObjectOutputStream oos = null;
        FileOutputStream fos = null;

        try {
            //oos = new ObjectOutputStream(new FileOutputStream("src\\favorite_recipe.txt"));
            fos = new FileOutputStream("src\\favorite_recipe.txt");
            fos.write(Recipe.favourite.name.getBytes());
        } finally {

            if (oos != null) oos.close();
            if (fos != null) fos.close();
        }

    }


    Recipe (String name, List<String> i, List<String> s){
        this.name = name;
        this.ingredients = i;
        this.steps = s;
    }

    public void print(){
        System.out.println(this.name);
        System.out.printf("\tingredients:\n");
        ingredients.forEach(i -> System.out.printf("\t\t%s\n", i));
        System.out.printf("\tsteps:\n");
        steps.forEach(s -> System.out.printf("\t\t%s\n", s));
        System.out.printf("\n");
    }


    public static void main (String [] args) throws IOException {

        // First exercise

        BufferedReader input = null;
        List<Recipe> recipeList = new ArrayList<>();

        try {
            input = new BufferedReader(new FileReader("src\\recipes.txt"));
            String str = "";

            while ((str = input.readLine()) != null) {

                while (str.equals(""))
                    str = input.readLine();

                String name = str;
                List<String> ingredients = new ArrayList<>();
                List<String> steps = new ArrayList<>();

                if ((str = input.readLine()).equals("ingredients:")) {
                    while (!(str = input.readLine()).equals("steps:")) {
                        ingredients.add(str);
                    }
                }

                str = input.readLine();

                while ( (str != null) && !str.equals("")  ) {
                    steps.add(str);
                    str = input.readLine();
                }

                recipeList.add(new Recipe(name, ingredients, steps));
                ingredients = null;
                steps = null;
                name = "";
            }

        } finally {
            if (input != null) input.close();
            recipeList.forEach(recipe -> recipe.print());
        }

        // Second Exercise

        Recipe.favourite = recipeList.get(0);

        Recipe.saveFavouriteToFile();




    }
}


