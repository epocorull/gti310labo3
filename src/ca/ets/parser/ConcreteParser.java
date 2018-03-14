package ca.ets.parser;

import ca.ets.FileInformation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *  This class implements the Parser interface.
 *  Concrete Parser reads the file whose path is indicated by the String filename
 *  and builds an instance of FileInformation.
 *
 *  @author Elsa Pocorull, Valentin Gaillard
 */
public class ConcreteParser implements Parser {

    /**
     * The parse method is designed to scan an input file and produced
     * an instance of FileInformation named fileInfo.
     * The parse method checks the file's format and extracts the useful information
     * in order to keep it in fileInfo.
     * @param filename The complete path to the file to parse.
     * @return fileInfo which is an instance of the FileInformation class.
     */
    @Override
    public Object parse(String filename) {
        int nb_sommet = 0;
        long val_infinie = 0;
        int sommet_depart = 0;
        int count_line = 0;
        FileInformation fileInfo = null;

        // Pour lire le fichier, le code suivant est inspiré de :
        // https://www.developpez.net/forums/d1214517/java/general-java/apis/io/lire-fichier-texte-java/
        try {
            InputStream flux = new FileInputStream(filename);
            InputStreamReader lecture = new InputStreamReader(flux);
            BufferedReader buff = new BufferedReader(lecture);
            String ligne;

            while ((ligne = buff.readLine()) != null) {

                if (count_line == 0) {
                    // extraction du nombre de sommets.
                    try {
                        nb_sommet = Integer.parseInt(ligne);
                        count_line += 1;
                    } catch (Exception e) {
                        System.out.println("fichier mal formaté");
                    }
                } else if (count_line == 1) {
                    //extraction de la valeur infinie.
                    try {
                        val_infinie = Long.parseLong(ligne);
                        count_line += 1;
                    } catch (Exception e) {
                        System.out.println("fichier mal formaté");
                    }
                } else if (count_line == 2) {
                    //extraction s'il y a de la valeur du sommet de départ, sinon il est laissé à 0
                    if (ligne.split("\t").length == 1) {
                        sommet_depart = Integer.parseInt(ligne);
                        fileInfo = new FileInformation(nb_sommet, val_infinie, sommet_depart);
                    } else {
                        fileInfo = new FileInformation(nb_sommet, val_infinie, sommet_depart);
                        int[] chemin = getInfo(ligne);
                        fileInfo.setChemin(chemin);
                    }
                    count_line += 1;
                } else {
                    if (!ligne.equals("$")) {
                        int[] chemin = getInfo(ligne);
                        if (fileInfo != null) {
                            fileInfo.setChemin(chemin);
                        }
                    }
                }
            }
            buff.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return fileInfo;
    }

    /**
     * The getInfo method creates a board which represents one way between two summits.
     * @param ligne The current String read by the buffer
     * @return int[] chemins
     */
    private int[] getInfo(String ligne) {

        int[] chemin = new int[3];

        String[] st_chemin = ligne.split("\t", -1);
        try {
            for (int i = 0; i < 3; i++) chemin[i] = Integer.parseInt(st_chemin[i]);

        } catch (Exception e) {
            System.out.println("fichier mal formaté");
        }

        return chemin;
    }
}