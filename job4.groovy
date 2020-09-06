job ("job4"){

    triggers{
        upstream('job3', 'SUCCESS')
    }

    steps{
        shell('''if kubectl get deploy | grep website
then 
exit 0
else 
kubectl create -f /kubecode/deploy.yml
fi

sleep 7

if kubectl get pods | grep running
then
exit 0
else 
echo "Pod is not running"
fi''')
    }
    
}  
