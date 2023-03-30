# Delete Blob

The functionality of the serverless function is to delete a blob or object in a container or bucket based on the request body.

[API_URL](https://ragul-engg.azurewebsites.net/api/deleteBlob)



```json
{
    "containerName":"container_name_of_the_blob",
    "blobName":"blob_name_to_delete"
}
```

I have added two containers for testing the API_ENDPOINT namely
- container
- files

Both contains five files for testing: file1.txt,file2.txt...

If the given container name is not present in the storage account or the blob is not available means, the response would be file not found.

# License Creation

The functionality of the serverless function is to create a license from the license body and store the body by encrypting it, in a license.txt file.

[API_URL](https://licensecreation.azurewebsites.net/api/createLicense)



```json
{
        "softwareName": "X Platform",
        "softwareVersion": "1.0.0.0",
        "softwareDescription": "xyz platform maintained by infosys",
        "vendorName": "Xyz Pvt Ltd",
        "vendorUrl": "https://xyz_company.com/",
        "grantedTo": "XYZ Learning",
        "licensedTo": "william01@xyz.com",
        "purpose": "PRODUCTION",
        "additionalInformation": {
            "use":"training"
        },
        "softwareModules": [
            {
                "moduleName": "sls",
                "moduleVersion": "1.0.0.0",
                "constraints": [
                    {
                        "name": "validFrom",
                        "value": "2022-01-01T10:08:12",
                        "operator": "DateFromOrAfter",
                        "errorMessage": "Product not licensed for usage"
                    },
                    {
                        "name": "validTo",
                        "value": "2023-01-01T10:08:11",
                        "operator": "DateUpto",
                        "errorMessage": "License expired"
                    }
                ],
                "module": {
                    "name": "sls"
                }
            }
       ]
}
```

## Mandatory Conditions

DateFromOrAfter and DateUpto are mandatory constrains in license.

## Strategy to be followed

- Public and Private RSA keypair should be configured.
- Secret key which is encrypted using the above RSA key pair.
- Software name should be configured and available in the list of software names using application.properties
  - application.softwareNames=A Platform, B Platform, C Platform, X Platform, Y Platform, Z Platform
- The licensed is validated using the provided constraints and converted into a string. Converted String is then appended with the secret key after it is decrypted using the private RSA key.
- The appended string <license_string>.<secret_key> is stored in a license[number].txt in Azure Storage Container.
