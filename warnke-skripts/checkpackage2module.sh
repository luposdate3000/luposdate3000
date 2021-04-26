for f in $(grep -r package src | grep "\.kt:" | sed "s-^src/luposdate3000_--g" | sed "s-/.*:package lupos.-:-g" | sed "s/\./_/g" | sort | uniq | grep -v package)
do
arrIN=(${f//:/ })
if [[ "${arrIN[0]}" == "${arrIN[1]}"* ]] || [[ "${arrIN[1]}" == "${arrIN[0]}"* ]] ;
then
echo ok >> /dev/null
else
echo wrong .. module "'" ${arrIN[0]} "'" contains package "'" ${arrIN[1]} "'"
fi
done
