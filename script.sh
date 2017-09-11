
ls -1|while read dirname; do
	i=0;
	if [ -d $dirname ]; then
		mkdir -p results/$dirname
		for filename in `find $dirname -name "dependency-check-report.xml"`
		do
    		i=$(($i + 1))
     	   cp $filename results/$dirname/dependency-check-report-${i}.xml
	   done
	fi
done