job ("job1"){
    scm{
        github('Yashmb/devops_task6','master')
    }

    triggers{
        githubPush()
    }

    steps{
        shell('''sudo rm -rf /web
sudo mkdir /web
sudo cp -rvf *.html /web
sleep 7
sudo rm -rf /kubecode
sudo mkdir /kubecode
sudo cp -rvf *.yml /kubecode''')
    }

}
