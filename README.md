About Application-
This Application solves problem of writing letter/email to each and every person when you have to send bulk emails or letters this is a way to go. Just provide your template and run the commands and you have letters/emails.

1. how to build --
 when you will run doAll, it will download the jar just do gradle sync and you can follow step2.

2. how to run --
open project go to main file, modify run configuration in the params pass the argument according to your need.
you can generate either letter or email.

Usage:
--email only generate email messages
--email-template <file> accept a filename that holds the
email template.
Required if --email is used
--letter only generate letters
--letter-template <file> accept a filename that holds
the email template.
Required if --letter is used
--output-dir <path> accept the name of a folder, all
output is placed in this folder
--csv-file <path> accept the name of the csv file to
process
Examples:
--email --email-template email-template.txt --output-dir
emails --csv-file customer.csv
--letter --letter-template letter-template.txt --output-
dir letters --csv-file customer.csv

voila! you will see letter/emails for all the customers.



