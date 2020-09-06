job ("job2"){

    triggers{
        upstream('job1', 'SUCCESS')
    }

    steps{
        shell('''if kubectl get pv | grep web-pv
then
echo "pv already exists"
kubectl apply -f /kubecode/pv.yml
else
kubectl create -f /kubecode/pv.yml
fi

sleep 7

if kubectl get pvc | grep web-pvc
then
echo "pvc already exists"
kubectl apply -f /kubecode/pvc.yml
else
kubectl create -f /kubecode/pvc.yml
fi

sleep 7

if kubectl get deploy | grep web
then
echo "deployment already exists"
kubectl apply -f /kubecode/deploy.yml
else
kubectl create -f /kubecode/deploy.yml
fi

sleep 7

rm -f pods
rm -f pod
echo $(kubectl get pods | grep website) >> pods
cut -d " " -f 1 pods >> pod
kubectl cp /web/web.html `cat pod`:/usr/local/apache2/htdocs/''')
    }

}
