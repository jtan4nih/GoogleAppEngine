:setEnv.cmd

echo on
set GAE_JAVA_SDK_HOME=C:\appengine-java-sdk-1.8.7
set PATH=%JAVA_HOME%\bin;%GAE_JAVA_SDK_HOME%\bin
mkdir c:\temp
set TEMP=c:\temp
set TMP=c:\temp

cd %~dp0

:http://code.google.com/appengine/docs/python/tools/uploadingdata.html#Downloading_and_Uploading_All_Data
:Genre,MovieID,CatalogNo,UPC,Title
:set .mode csv and .output xmm.csv.tmp in sqlite3 and iconv -f iso-8859-1 -t utf-8 xmm.csv.tmp > xmm.csv
:Do not forget to add "import_transform: transform.create_foreign_key('Kind', key_is_id=True)"
:based on http://stackoverflow.com/questions/3129286/google-app-engine-bulkloader-problem-when-using-yaml-autogenerated-configuration/5782403#5782403
:https://developers.google.com/appengine/docs/python/tools/uploadingdata

:c:\google_appengine\appcfg.py create_bulkloader_config --filename=bulkloader1.yaml --url=http://cloudaware.appspot.com/remote_api

REM ********************************************************************************************************************************************
REM DO NOT FORGET TO CHANGE remove the __key__ or id property before upload and change bulkloader1.yaml to bulkloader.yaml!!!
REM Also need to make sure that your datastore properties ARE THE SAME as the one specified in .yaml (like there SHOULD NOT BE ANY MISSING properties observed using Data Viewer)
REM ********************************************************************************************************************************************
:c:\google_appengine\appcfg.py upload_data --dry_run --application=cloudaware --config_file=bulkloader.yaml --filename=download_kind_ServiceRegistry.csv --kind=ServiceRegistry --url=http://cloudaware.appspot.com/remote_api
:c:\google_appengine\appcfg.py upload_data --config_file=bulkloader.yaml --filename=download_kind_ServiceRegistry.csv --kind=ServiceRegistry --url=http://cloudaware.appspot.com/remote_api

:c:\google_appengine\appcfg.py upload_data --dry_run --application=cloudaware --config_file=bulkloader.yaml --filename=download_kind_Huma.csv --kind=Huma --url=http://cloudaware.appspot.com/remote_api
:c:\google_appengine\appcfg.py upload_data --config_file=bulkloader.yaml --filename=download_kind_Huma.csv --kind=Huma --url=http://cloudaware.appspot.com/remote_api
:c:\google_appengine\appcfg.py upload_data --config_file=bulkloader.yaml --filename=download_kind_Geniu.csv --kind=Geniu --url=http://cloudaware.appspot.com/remote_api
:c:\google_appengine\appcfg.py upload_data --config_file=bulkloader.yaml --filename=download_kind_Secure.csv --kind=Secure --url=http://cloud-service-api.appspot.com/remote_api
c:\google_appengine\appcfg.py upload_data --config_file=bulkloader.yaml --filename=download_kind_Movie.csv --kind=Movie --url=http://cloudaware.appspot.com/remote_api

del bulkloader-log*
del bulkloader-progress*
del bulkloader-results*

pause