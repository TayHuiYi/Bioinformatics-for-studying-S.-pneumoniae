import com.sun.jdi.*;
import org.biojava.nbio.core.exceptions.*;
import org.biojava.nbio.core.sequence.io.embl.*;

import java.io.*;

public class GT_Extraction {
    public static void main (String[] args) throws CompoundNotFoundException, IOException {
        //IMPORTANT NOTE ON EMBL FILE USED: ID needs to removed due to some problems with BioJava that makes it unable to process the ID (e.g. ID   28A_pne57  standard; DNA; UNC; 22978 BP)
        //Note down ID details beforehand if needed

        File file = new File("*"); //provide path to the wanted serotype 
        EmblReader er = new EmblReader(); //Object to read EMBL file
        EmblRecord data = er.process(file); //Process data in EMBL file
        String sequences = data.getSequence(); //Get DNA sequence from EMBL file

        BufferedReader br = new BufferedReader(new FileReader(file)); //Object to read contents of EMBL file (line by line)

        //Create output file to store DNA sequence extracted
        File fout = new File("*"); //provide new file name to store GT sequences extracted 
        FileOutputStream fos = new FileOutputStream(fout);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        String line;
        while ((line= br.readLine()) != null){
            if (line.substring(0,2).equals("FH")){
                continue;
            }
            if (line.substring(0,2).equals("SQ")){
                break;
            }
            else if (line.substring(0, 8).equals("FT   CDS")){
                String location = line.substring(21, line.length()); //location of gene
                String geneName = "";
                Boolean GT = Boolean.FALSE;
                br.mark(1000); //allows reader to "peek" at the entries and restart at the wanted position
                String info;
                int count = 0;
                while ((info=br.readLine())!=null){
                    if (info.substring(0, 8).equals("FT   CDS")|| info.substring(0,2).equals("SQ")) {
                        br.reset();
                        break;
                    }
                    count+=1;
                }
                for (int i =0;i<count;i++){
                    line = br.readLine();
                    //System.out.println(line);
                    if (line.substring(21,line.length()).equals("/product=\"putative glycosyl transferase\"")||line.substring(21,line.length()).equals("/product=\"putative rhamnosyl transferase WchF\"")){
                        GT = Boolean.TRUE;
                    }
                    if (line.substring(21,23).equals("/g")) {
                        geneName = line.substring(21,line.length());
                    }
                }
                if (GT == Boolean.TRUE){
                    //find out the position of ".."
                    int stop=0;
                    for (int i=0; i<location.length()-2;i++){
                        if (location.substring(i,i+2).equals("..")) {
                            stop = i;
                        }
                    }

                    //Get the start and end of the location of the gene wanted
                    int start = Integer.parseInt(location.substring(0,stop))-1;
                    int end = Integer.parseInt(location.substring(stop+2,location.length()));

                    //write nucleotide sequence into the new output file initialized earlier
                    bw.write(geneName);
                    bw.newLine();
                    bw.write(sequences.substring(start,end));
                    bw.newLine();
                }
            }
        }
        bw.close();
    }
}
