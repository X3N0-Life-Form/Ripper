# Ripper
Cross-platform, java-based mass unpacker.



Requirements
------------
- SevenZip JBindings (included)
- Java 7
- Ant

Building
--------
TODO (will be using ant)

Usage
-----
##### Command line
`java -jar ripper.jar [MODE] [OPTION [FOLDER]]`

##### In *Key* mode:
- Extract archives contained in a specified directory
- The content of these archives is extracted in another directory
- Each archive has its own sub-directory with extracted content

##### In *Knife* mode (extension of *Key* mode):
- Requires an extra *knowledge* directory
- Specify a range of passwords to try
- Attemps to extract archive content using passwords in the specified range
- Attempted password ranges & valid passwords are stored in the *knowledge* directory
- Previous valid passwords will be tested before exploring the specified range of passwords

Options
-------
**Note:** Options can be specified through either directly through the command line, or through an option file.