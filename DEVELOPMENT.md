## Development

This file is intended for when working with the project.

### Database

The project is expecting a **mongodb** database to be present, the simplest setup locally
is to setup a docker.

Inside **docker/mongodb** folder there is a docker-compose.yml which starts up
a mongodb server with:

- port
    - 27017
- username
    - root
- password
    - example

admin interface reachable at:
[http://localhost:8081](http://localhost:8081)

### Resources

Resources are served based on the SATISFACTORY_RESOURCE_PATH environment variable.
After a successful import you can visit the follow link as an example to get
the resource.

localhost:8000/resources/**{gameVersion}**
/Game/FactoryGame/Buildable/Factory/AssemblerMk1/UI/IconDesc_AssemblerMk1_256.png

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
``
.\umodel.exe -path="C:\Program Files\Epic Games\<version of the game>\FactoryGame\Content\Paks" -out=".\out256" -png -export *512*.uasset -game="ue4.26"
``

#### Making the import file

1. Create a new temporary folder
2. Put the Docs.json file into that folder
3. Export all the Pak content (previous section), and move all the contents of export into this folder

- Only the content, not the full folder which was created during export

4. Make a ZIP of all the content inside the temporary folder to be used for the import