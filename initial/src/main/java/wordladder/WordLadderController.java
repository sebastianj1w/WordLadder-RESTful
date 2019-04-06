package wordladder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WordLadderController {

    private Set<String> dict = loadDict("static/dictionary.txt");

    @RequestMapping("/word_ladder")
    public WordLadder wordladder() {
        return new WordLadder("word", "lade", dict);
    }


    private static Set<String> loadDict(String path) {
        Set<String> wordSet = new TreeSet<>();
        try {
            System.out.println("loading");
            org.springframework.core.io.Resource resource = new ClassPathResource(path);
            File dictFile = resource.getFile();

            if (!dictFile.exists()) {
                System.out.println("dict not exits");
                return new TreeSet<>();
            }

            System.out.println("dict exits");


            BufferedReader reader = new BufferedReader(new FileReader(dictFile));
            String tempString;
//        int line = 1;

            while ((tempString = reader.readLine()) != null) {
                wordSet.add(tempString);
//            line++;
            }

            reader.close();
        } catch (java.io.IOException E) {
            System.out.println("ERROR");
        }
        return wordSet;
    }
}