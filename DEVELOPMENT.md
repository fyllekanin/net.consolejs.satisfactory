## Development

This file is intended for when working with the project.

### Satisfactory files

There is x2 files from Satisfactory needed when doing the import.
These files will not be included in the actual project itself but there
will be a seeded version of them included in the database seed.

#### Docs.json

``
C:\Program Files\Epic Games\SatisfactoryEarlyAccess\CommunityResources\Docs\Docs.json
``

#### .pak and .sig files

``
C:\Program Files\Epic Games\<version of the game>\FactoryGame\Content\Paks
``

#### How to export files

``
.\umodel.exe -path="C:\Program Files\Epic Games\<version of the game>\FactoryGame\Content\Paks" -out=".\out256" -png -export *256*.uasset -game="ue4.26"
``

#### Making the import file

1. Create a new temporary folder
2. Put the Docs.json file into that folder
3. Export all the Pak content (previous section), and move all the contents of export into this folder

- Only the content, not the full folder which was created during export

4. 