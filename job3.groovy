job ("job3"){

    triggers{
        upstream('job2', 'SUCCESS')
    }

    steps{
        shell('''export status=$(curl -o /dev/null -sw "%{http_code}" http://192.168.99.105:31000/web.html)
if [ $status==200 ]
then 
echo "Deployed Successfully"
exit 0
else
echo "status_code:$status"
exit 1
fi''')
    }
    
    publishers {
        extendedEmail {
            recipientList('gswamy915@gmail.com')
            defaultSubject('Build Status')
            defaultContent('Build Failed')
            contentType('text/html')
            triggers {
                failure{
                    attachBuildLog(true)
                    subject('Build Failed')
                    content('Build Failed')
                    sendTo {
                        developers()
                    }
                }
            }
        }
    }
}  
