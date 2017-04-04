#
#
#
function buildService() {
echo "building Service"
echo $SERVICE_LOC
cd $SERVICE_LOC
mvn install 
exit 0
}
function checkMaven() {
echo "Checking if Maven already downloaded and extracted"

file="apache-maven-3.2.2-bin.tar.gz"
if [ -f "$file" ];
then
    if [ -d "apache-maven-3.2.2" ];
        then
	echo "Maven already downloaded and extracted"
        setupMaven
        if [ $? -eq 0 ];then
        buildService
       fi
    fi
fi
return 0;
}

function downloadAndExtractMaven() {

echo "Downloading and setting up Maven..."

echo "Downloading Maven now..."

# Execute wget to download maven
wget http://mirror.olnevhost.net/pub/apache/maven/binaries/apache-maven-3.2.2-bin.tar.gz

OUT=$?
if [ $OUT -eq 0 ];then
   echo "Maven downloaded successfully...extracting now!"
else
   echo "Maven could not be downloaded... do you even have internet !!!"
   echo " shutting down the script offensively !"
   exit
fi

echo " Extracting Maven..."

# Extracting Maven Tar
tar xvf apache-maven-3.2.2-bin.tar.gz

OUT=$?
if [ $OUT -eq 0 ];then
   echo "Maven Extraction successful"
else
   echo "Maven could not be extracted... pls atleast own the directory!!!"
   echo " shutting down the script offensively !"
   exit
fi
}
function setupMaven() {

echo " Setting up Maven..."
Basedir=$(pwd)
echo $Basedir
export MAVEN_OPTS="-Xmx1024m"
export M2_HOME=$Basedir/apache-maven-3.2.2
export M2=$M2_HOME/bin
export PATH=$M2/:$PATH

echo " Checking if Maven Setup successful"

mvn -version

OUT=$?
if [ $OUT -eq 0 ];then
   echo "Maven Set up successful..."
   return 0
else
   echo "Maven could not be setup... try manual kido!!!"
   echo "shutting down the script offensively !"
   exit 1
fi
}
#
# Setting Environment
#
. ./setenv.sh

#
# Starting Script - 1) Maven 2)HomeService 3)build and deploy
#
echo "Starting script"
#
# Check if Maven already downloaded and extracted
#
checkMaven

if [ $? -eq 0 ];then
#
# Downloading and extracting maven if not already done
#
echo "Maven not found."
downloadAndExtractMaven
setupMaven
buildService
fi

