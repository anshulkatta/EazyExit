#
# Deployment of HomeAccessService
#
file=$(pwd)/"Deployable.tar"
tomcattar="apache-tomcat-7.0.75.tar.gz"
tomcat="apache-tomcat-7.0.75"
service="HomeAccessService-1.war"

function untarDeployable() {
if [ -f "$file" ]; then
   echo $file
   echo "File Exists...Extracting"
   mkdir Deployable
   tar xvf $file -C Deployable
   cd Deployable
   chmod 777 * 
   return 0
else
   echo $file
   echo "File does not exist...please check $file is present"
   return 1
fi
}

function deployService() {
echo "Copying to tomcat\webapp..."
cp $service $tomcat/webapps
}

function untarTomcat() {

tar xvf $tomcattar 

if [ $? -eq 0 ]; then
echo "Tomcat Extracted"
fi

}

function startTomcat() {
echo "Tomcat starting"
cd $tomcat
cd bin
./startup.sh
return 0
}


#
# UnZIP TAR 
#

untarDeployable

out=$?

if [ $out -eq 0 ]; then
#
# UnZIP Apache Tomcat
#
untarTomcat
else
echo "extraction of Tomcat failed"
exit 1
fi

#
# Deploy Service
#
deployService

#
# Start Tomcat
#
startTomcat
out=$?
if [ $out -eq 0 ];
then
echo "Service is deployed"
else
echo "Service did not deployed"
fi





