#!/bin/sh
cd ./report/html
echo "Enter report logfile path."
sed -i '' 's/ltltlt/</g' *
sed -i '' 's/gtgtgt/>/g' *
echo "Exec sed OK!"


