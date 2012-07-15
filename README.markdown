Build Instructions
=========================

The basic Java portion of the project should build and run fine from a recent version of Eclipse. I used Eclipse Juno to create the project so I can not vouch for older versions but there is nothing tricky so it should work with minimal modification in any modern Java IDE.

I played around a bit with writing some Specification based tests using Specs2. To run these you will need to work from the command line. I created and ran everything on OS X so you may need to change things if you are on a different platform.

# Download and install sbt (simple build tool) - https://github.com/harrah/xsbt
# Download and install Specs2 https://github.com/etorreborre/specs2
# Change to the directory where you cloned this (DeckOfCards) repository
# Then run:
        > sbt clean compile test
