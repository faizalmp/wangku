import constants.Dependencies
import utils.implementLibs

plugins { id("lib-module") }

dependencies { implementLibs(Dependencies.dataDependencies) }