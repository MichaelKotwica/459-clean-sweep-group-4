# Clean Sweep Robotic Vacuum Cleaner & Sensor Simulator
## Github Actions Build Status:  [![Java CI with Maven](https://github.com/MichaelKotwica/459-clean-sweep-group-4/actions/workflows/maven.yml/badge.svg)](https://github.com/MichaelKotwica/459-clean-sweep-group-4/actions/workflows/maven.yml)
## Agile Software Development Fall 2024 Clean Sweep Project
### Group 4 Members
- Daniel​
- Janki​
- Manish
- Michael​
- Yash
### Floor Plan
![FP2](https://github.com/user-attachments/assets/7cd9d8ef-1179-4a4f-bde9-f22c810e7952)
### Terminal output (Clean Sweep Actions Ommited for Brevity)
```
22:06:58.580 [main] INFO  com.group4.FloorPlan - Guest Bedroom B
22:06:58.583 [main] INFO  com.group4.FloorPlan - Top Middle Bathroom
22:06:58.583 [main] INFO  com.group4.FloorPlan - Top Right Closet
22:06:58.583 [main] INFO  com.group4.FloorPlan - End of Hallway Bathroom
22:06:58.583 [main] INFO  com.group4.FloorPlan - Master Bedroom
22:06:58.583 [main] INFO  com.group4.FloorPlan - Hallway
22:06:58.584 [main] INFO  com.group4.FloorPlan - Left Closet on Left Side
22:06:58.584 [main] INFO  com.group4.FloorPlan - Right Closet on Left Side
22:06:58.586 [main] INFO  com.group4.FloorPlan - Left Closet Between Guest Bedroom A and Smaller Closets
22:06:58.586 [main] INFO  com.group4.FloorPlan - Guest Bedroom A
22:06:58.587 [main] INFO  com.group4.FloorPlan - createSampleFloorPlan Complete
========================================================================
                 Floor Plan:              
 /  /  /  /  /  O  B  B  B  B  B  B  B  B 
 O  O  /  /  /  /  B  B  B  B  B  B  B  B 
 /  /  /  /  /  /  B  B  B  B  B  B  B  B 
 B  B  B  B  B  B  B  C  B  B  B  B  B  B 
 B  B  B  B  B  B  B  B  B  B  \  \  \  B 
 C  /  /  /  /  /  B  B  B  B  \  \  \  B 
 /  /  /  /  /  /  B  B  B  B  \  \  \  B 
 /  /  /  /  /  /  B  B  B  B  \  \  \  B 
 /  /  /  /  /  /  B  B  B  B  \  \  \  B 
 /  /  /  /  /  /  S  B  B  B  B  B  B  B 

                     Key:                        
    B = Bare Floor          / = Low Pile Carpet  
    S = Stairs              \ = High Pile Carpet
    O = Obstacle            C = Charging Station

========================================================================
                       Dirt Amount Before Cleaning:                       
    2    0    1    2    1    X    2    0    3    2    3    3    0    0
    X    X    1    1    2    0    0    3    3    2    2    0    1    2
    3    0    3    2    0    2    2    0    1    3    2    1    0    2
    3    2    3    0    2    2    3    0    0    3    2    0    1    3
    0    3    0    2    0    3    1    1    1    1    0    0    2    2
    2    3    3    0    1    3    2    2    1    0    1    0    1    0
    2    3    3    1    0    3    1    1    2    0    2    0    0    1
    0    3    1    3    3    0    1    0    3    3    2    3    3    0
    2    2    3    2    0    0    3    1    1    1    1    2    2    3
    2    2    2    1    1    0    X    1    3    2    3    0    3    0

========================================================================
                Floor Plan Dirt Remaining After Cleaning:               
    2    0    0    0    0    X    2    0    0    0    0    3    0    0
    X    X    0    0    2    0    0    3    3    0    0    0    0    0
    0    0    0    0    0    0    0    0    0    0    0    0    0    0
    3    2    3    0    1    0    0    0    0    0    0    0    0    0
    0    3    0    2    0    3    0    0    0    0    0    0    0    0
    0    0    0    0    0    0    0    0    0    0    0    0    0    0
    0    0    0    0    0    0    0    0    0    0    0    0    0    0
    0    0    0    0    0    0    0    0    0    0    0    0    0    0
    0    0    0    0    0    0    0    0    0    0    1    0    0    0
    0    0    2    1    1    0    X    0    0    0    0    0    0    0

========================================================================
                    Clean Sweep Info After Cleaning:                      
  Powered On: false   Dirt Capacity: 16/50   Battery Level: 250.0/250.0
```

## Clean Sweep Robotic Vacuum Cleaner & Sensor Simulator
Your company has assembled a small team of developers and business people to work on the
implementation of the Clean Sweep robotic vacuum cleaner control system.
### 1. Control System
The control system integrates the Clean Sweep hardware components so that the vacuum can
successfully navigate and clean a typical home.
#### 1.1 Navigation
The Clean Sweep roams around the home sweeping up any dirt that it finds. Therefore it must
be able to discover the basic floor plan of a home based on the input from its sensors. The Clean
Sweep has four directional sensors around its perimeter that detect when it is about to encounter
an obstacle. The control system must interpret the output from these sensors and stop the vacuum
from running into any obstacles. Upon encountering an obstacle, the Clean Sweep must
determine a new direction that prevents it from running into another obstacle. If the Clean Sweep
is unable to move, it should shut itself down and wait for the customer to move it to a new location.

Under no circumstances should the Clean Sweep attempt to traverse stairs. A sensor on the bottom
of the Clean Sweep can detect the edge of stairs or other declines. If this sensor detects anything,
the control system should treat it as though it had encountered any other kind of obstacle.

The Clean Sweep can move in four directions. If you envision the Clean Sweep as traversing a grid,
and assume that it is located at position (x, y), then it can move to positions: (x+1, y), (x−1, y),
(x, y +1), and (x, y −1). There is no need for the vacuum to change its orientation or turn around
in order to navigate.

#### 1.2 Dirt Detection and Cleaning
As the Clean Sweep moves, a sensor determines if there is dirt to collect. If so, the control system
should tell the vacuum to sweep up the dirt. The sensor is not able to tell how much dirt is present,
only whether or not the current location is considered clean. Therefore multiple uses of the vacuum
may be required to clean a particularly dirty area.

The Clean Sweep is equipped with a surface sensor that allows it to detect whether or not the
surface it is currently traversing is bare floor, low-pile carpet, or high-pile carpet. It should automatically
shift its cleaning mode between the surfaces accordingly.

Each use of the vacuum removes 1 unit of dirt. The Clean Sweep has a dirt-carrying capacity of
50 units. Once that capacity has been reached, it should return to its charging base and turn on
its Empty Me indicator. The Clean Sweep doesn’t stop cleaning until:
- It has visited every accessible location on the current floor.
- Its dirt capacity has been filled.
- It only has enough power remaining to return to its charging station. See Power Management
for more details.

Each time the Clean Sweep begins a cleaning cycle, it automatically assumes that each location is
dirty. A cleaning cycle is defined as the start of the next cleaning after all reachable areas have
been cleaned. So, if the Clean Sweep is partially done cleaning a floor and has returned to its base
station to recharge, it will assume that the current cleaning cycle is not yet complete.

### 1.3 Power Management
The Clean Sweep has a limited battery life of 250 units of charge. Each movement and vacuum
operation requires units of charge depending on the surfaces being traversed:
- Bare floor - 1 unit
- Low-pile carpet - 2 units
- High-pile carpet - 3 units

The charge required for the Clean Sweep to move from location A to location B is the average of
the required charge costs for the surfaces at the two locations. For example, if the Clean Sweep
moves from a bare floor to low-pile carpet, then the charge required is 1.5 units. It costs the same
amount of charge to clean the current location is it does to traverse that location.

The Clean Sweep should always return to its charging station before it runs out of power. Homeowners
that come home and discover their robotic vacuum out of power in the middle of a room
tend to be dissatisfied customers. As it returns to its charging station, the Clean Sweep will not
perform any cleaning. Upon returning to its charging station, the Clean Sweep will automatically
re-charge to full capacity. Once re-charged, it will resume cleaning until it detects that it has
visited every accessible location on the current floor.

### 1.4 Diagnostics and Troubleshooting
Since the Clean Sweep is a new product, it is important that technical support personnel are able
to troubleshoot its operation.
#### 1.4.1 Activity Log
On request, the Clean Sweep should be able to dump a log of its activity for the latest cleaning.
This log should provide relevant information including:
- Sensor checks.
- Movement.
- Cleaning.
- Power remaining after each activity.
- Recharging.
## 2 Sensor Simulator
The Clean Sweep must maintain an internal map of the home. In order to write the control software,
it is necessary to simulate the input of the Clean Sweep’s sensor array as it might respond within
actual homes. The Sensor Simulator provides this capability and acts as a virtual sensor array.
These simulations will act as test data against which you can test your control software.
### 2.1 Floor Plan Representation
You will need to define a persistent floor plan layout. The layout file will need to represent different
attributes of floor plan.
#### 2.1.1 Layout Files
To facilitate the testing of the Clean Sweep, the Sensor Simulator must be able to “play back”
pre-defined floor plans. This means that the Sensor Simulator should be able to read a floor plan
file and use that file to respond to requests from the Clean Sweep’s control software.

The control software itself should have no knowledge of the floor plan layout. This file is only
intended to allow the sensor simulator to emit controlled output based on interactions with the
control software. The fact that the entire floor plan is available to the sensor simulator does not
mean that the Clean Sweep has a priori knowledge of the home’s layout. The Clean Sweep control
system must discover the floor plan in real time just as the physical device would.
#### 2.1.2 Layout
A floor plan is a grid comprised of a series of cells. Each cell is adjacent
to at most 8 other cells.

The Clean Sweep can move 90◦ in any direction. Consider the figure to
the right. If the Clean Sweep is on cell E, it can move to cells B, D, F
and H. Similarly, if it is on cell A, it can only move to cells B and D.
#### 2.1.3 Surfaces
The surface sensor detects the surface of the cell. This determines how
much power is required to move into and clean that cell. The surface sensor can identify the
following floor coverings:
1. Bare floor (e.g. wood, linoleum, etc.)
2. Low-pile carpet
3. High-pile carpet

The dirt sensor can tell whether or not a given cell must be cleaned. The simulator must be able
to represent how many units of dirt are at a given location. While the sensor itself cannot tell
exactly how many units are present, this will allow the simulator to continuously register a cell as
dirty until the appropriate number of vacuum operations have been performed.
#### 2.1.4 Navigation
The Clean Sweep has navigation sensors that allow it to move around the floor plan. The floor
plan must identify where the Clean Sweep may navigate and when there are obstacles to its path.
The floor plan must indicate whether there is a path from one cell to another or whether such
navigation is blocked. Each path between two cells can contain the following:
1. Unknown. The Clean Sweep doesn’t yet know what’s in the specified direction.
2. Open. The sensor indicates that the path is open.
3. Obstacle. The sensor indicates that the path is obstructed.
4. Stairs. The sensor indicates that the path in that direction is blocked by stairs.
#### 2.1.5 Charging Stations
Each floor plan may contain one or more charging stations. These charging stations are used by
the Clean Sweep to replenish its battery when it runs out of power. The Clean Sweep always knows
about the charging station where it begins its cleaning process. It can discover others if it is within
2 cells of the charging station. The detection of the charging station happens whether or not the
path to the station is actually clear. For example, it is possible that the Clean Sweep could detect
a charging station in another room, but not be able to get to that station if the room’s door is closed.

The control system must identify cells that contain charging stations. As the vacuum sweeps the
floor, it will continually track which charging station is closest to its current position and move to
that station when necessary.
