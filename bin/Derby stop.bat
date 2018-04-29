@echo off
set DERBY_HOME=C:\Programs\Genuitec\MyEclipse 10\MyEclipse 10\configuration\org.eclipse.osgi\bundles\19\1\.cp
start "Derby" java -jar "%DERBY_HOME%\lib\derbyrun.jar" server shutdown