#!/bin/bash
####################################################################
# Convenience script to execute a maven build on all jobs
#
# Args:  optional mvn goals/arguments -- defaults to "clean package"
#
####################################################################

function usage {
    echo "usage: `basename $0` <package> [--start <start>] [--end <end>] [--stack <stack>] "
    echo "where:"
    echo ""
    echo "    package - is the path to the package tarball/zip file that"
    echo "              will be pushed into staging."
    echo "    start   - ISO 8601 UTC start date/time.  Defaults to now or"
    echo "              where the last version of this job left off"
    echo "    end     - ISO 8601 UTC end date/time.  Defaults to 'run"
    echo "              indefinitely"
    echo "    stack   - Stack in which this job will run, default to staging"
    echo ""
    exit 1
}

if [ $# -lt 1 ]; then
    usage
fi

PACKAGE=$1
START_ARG=
END_ARG=
STACK=dev
shift
JOBS_DIR=`dirname $0`

if [ ! -f "$PACKAGE" ]; then
    echo "Package does not exist: $PACKAGE"
    exit 1
fi

filename=`basename $PACKAGE`
JOB_NAME="${filename%.*}"


while [ $# -gt 0 ]; do
    case "$1" in
    --help|-h|-\?)
        usage
        exit 0
    ;;
    --start)
        shift
        START_ARG=$1
        shift
    ;;
    --end)
        shift
        END_ARG=$1
        shift
    ;;
    --stack)
        shift
        STACK=$1
        shift
    ;;
    *)
        echo "Unknown argument: $1"
        usage
        exit 1
        ;;
     esac
done

TARGET_DIR=`pwd`/`dirname $PACKAGE`
SRC_DIR=`pwd`/src

rm -rf ${TARGET_DIR}/${JOB_NAME}
mkdir -p ${TARGET_DIR}/${JOB_NAME}/lib/
cp ${TARGET_DIR}/${JOB_NAME}.jar ${TARGET_DIR}/${JOB_NAME}/lib/
cp ${TARGET_DIR}/${JOB_NAME}.jar ${TARGET_DIR}/${JOB_NAME}/lib/
cp -r ${SRC_DIR}/main/resources/java-main/ ${TARGET_DIR}/${JOB_NAME}/
cat > ${TARGET_DIR}/coordinator.properties << EOF
start=${START_ARG}
end=${END_ARG}
nameNode=hdfs://localhost:8020
jobTracker=localhost:8032
queueName=default
zooKeeperQuorum=localhost
zooKeeperPort=2181
oozie.wf.application.path=hdfs://localhost:8020/apps/${JOB_NAME}
EOF
hadoop fs -rm -r /apps/${JOB_NAME}
hadoop fs -mkdir /apps/${JOB_NAME}
hadoop fs -copyFromLocal ${TARGET_DIR}/${JOB_NAME} /apps/

#oozie job -oozie http://aagarwal-mbpro.local:11000/oozie -config ${TARGET_DIR}/coordinator.properties -run
#oozie job -oozie http://aagarwal-mbpro.local:11000/oozie -config /apps/${JOB_NAME}/job.properties -run
#oozie job -oozie http://aagarwal-mbpro.local:11000/oozie -config ${SRC_DIR}/main/resources/backup/job.properties -run
oozie job -oozie http://localhost:11000/oozie -config ${SRC_DIR}/main/resources/backup/job.properties -run

if [ $? -ne 0 ]; then
    echo "Unable to upload package."
    exit 2
fi

echo ""
echo "Successfully installed $PACKAGE to ${STACK}"
echo ""




