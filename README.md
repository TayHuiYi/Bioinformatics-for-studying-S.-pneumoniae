Codes in this repo has been written by Eugene Gan, Ng Jing Ting and Tay Hui Yi for SP3172 - Decoding Capsule Synthesis, under Prof. Sham Lok To.

# AlphaFold-Colouring with pLDDT values-
The notebook allows users to colour their structures generated from AlphaFold with the pLDDT values generated. 
A new PDB file with the b-factor column edited with pLDDT values will be created. Opening the file with softwares such as PyMol will allow the user to view the coloured structure.

**Note**
- BioPython is needed to run the code 

# Extraction of glycosyltransferase sequences
The notebook, "GT_Extraction.java", allows users to extract glycosyltransferase (GT) sequences from the genomes of *S. pneumoniae* serotypes. A new fasta file is generated with the nucleotide sequence of each GT of a particular serotype.

It is possible to loop the code to extract the GTs of multiple serotypes at a time, though it is not included in the notebook.

**Brief instructions** 
1. Remove ID details (e.g. ID   35F_pne74  standard; DNA; UNC; 15137 BP.)
2. Indicate path to the embl file containing the serotypes genome under the variable, "file" 
3. Indicate path and name of the new text file generated under the variable, "fout"
4. Run the code 

**Note**
- BioJava is needed to run the code 

# Notes for all notebooks 
- Detailed explanation and instructions are within the notebook, commented out.
- The code in all notebooks can be looped to edit multiple files at a time, though it is not included in the notebooks.


