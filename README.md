# ROACH Ground Station Software

ROACH project is a rover designed to explore the hull of a spacecraft for structural damages caused by space debriefs. It can even perform minor repairs on site. The rover uses electroadhesive pads to assist its movements on the hull. Power and computer connectivity will be supplied by the cable used to secure the rover to the spacecraft.

The ROACH rover will collect a set of data related to the hull of the spacecraft as well as the rover itself. These data will support ground team with the control and continuous monitoring the rover. Major part of the data set will consist of;

- Motional data of the rover movements monitored by two cameras
- Status of the electroadhesive pads
- Distance data to monitor if the rover has detached in microgravity
- Acceleration data
- Thermal data

This set of data will be collected by the processing unit in the spacecraft and transmitted to the ground station software by serial transmission.

ROACH Ground Station Software is a Java application compiled with features to receive data streams and control the ROACH rover.

## Getting Started

To build the project;
1. Download the repository to your local PC
2. Open Net Beans IDE and browse to the download location
3. Open project and setup dependencies

### Prerequisites

- `Net Beans IDE` [Download](https://netbeans.org/downloads/)

- Libraries
1. `Orson Charts 1.6` -> [Download](https://s3-eu-west-1.amazonaws.com/orson-charts-public/orsoncharts-1.6.zip)
2. `Hamcrest Core 1.3` -> [Download](http://central.maven.org/maven2/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar)
3. `Commons Math 3.6` -> [Download](http://central.maven.org/maven2/org/apache/commons/commons-math3/3.6/commons-math3-3.6.jar)
4. `jFreeChart 1.0.19` -> [Download](http://central.maven.org/maven2/org/jfree/jfreechart/1.0.19/jfreechart-1.0.19.jar)
5. `Servlet` -> [Download](http://www.java2s.com/Code/JarDownload/servlet/servlet.jar.zip)
6. `SWT Graphics 2D` -> [Download](http://central.maven.org/maven2/org/jfree/swtgraphics2d/1.0/swtgraphics2d-1.0.jar)
7. `jUnit 4.11` -> [Download](http://central.maven.org/maven2/junit/junit/4.11/junit-4.11.jar)
8. `jCommon 1.0.23` -> [Download](http://central.maven.org/maven2/org/jfree/jcommon/1.0.23/jcommon-1.0.23.jar)
9. `Orson PDF 1.7` -> [Download](https://s3-eu-west-1.amazonaws.com/orsonpdf-public/orsonpdf-1.7.zip)
10. `jFree SVG 2.0` -> [Download](http://central.maven.org/maven2/org/jfree/jfreesvg/2.0/jfreesvg-2.0.jar)


## Contributing

Please read [CONTRIBUTING.md](#) for details on our code of conduct, and the process for submitting pull requests to us.

## Authors

* **Valentin Starlinger** - *Initial Work* - [vstarlinger](https://github.com/vstarlinger)

See also the list of [contributors](#) who participated in this project.
