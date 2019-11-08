# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/).

## [0.0.0] - 2019-09-21

### Added
- `CHANGELOG.md`
- `TestScheduling.scala` - testfile for scheduling ops 
- `runTests.py` - python script to run all test with "someString" in the filename on "someFile.png"  
  `python runTests -f someString -i someFile.png`
- height and width diff for pipelines with no input


### Changed
- dimension and shadowing names 
    * innermost dimensions name and shadowing name is "x" or "y" 


- scheduling ops
    * `storeAt`, `computeAt` can be used in any order
    * `storeAt` functions higher than `computeAt` function
    * allow splitting of inner dimensions
    

### Fixed (?)
- dimensions and bounds
    * all calls to `boundsForProdInCon` use `shadowingName` which always is "x" or "y" 
    
    
- loop bound calculation
    * different approach for calculating of loop bounds
    * correct output for examples with deeper nested schedules
    * correct output for examples with different compute/store level
    * not sure about using enclosingVars instead of computeAt var
    
    
- computing storage offset
    * use storeAt when computing offsets
    
### TODO   
- fused dimensions
    * what should be their name and how about bounds    
    
     
- notPreviouslyComputed
- tests failing for bounds