#include <stdio.h>
#include <stdlib.h>
#include<string.h>
#define TRUE 1
#define FAlSE 0

//login for user ID

 int  NEW_Login(int userid)
 {
 	printf("\n enter the information:");
        
	struct user
	{
	 char userName[50];
	 char mobileNo[10];
	 char password[8];
	 int userId;
	}s1;
	FILE *userptr;
	int succsess;
 		
	s1.userId=userid;

	userptr=fopen("user.txt","a+");
		if(userptr==NULL)
	 	{
	  		printf("\n file does not exist ");
	    	exit(0);
	    }
	    
	    //prints the user data into the file
	    
	    else
	    {
            printf("\n enter the name:\n");
            scanf("%s",s1.userName);
            fprintf(userptr,"%d\t%s\t",s1.userId,s1.userName);
            
            printf("\n enter mobile no:");
            scanf("%s",s1.mobileNo);
            fprintf(userptr,"%s\t",s1.mobileNo);
	
            printf("\n enter the password:");
            scanf("%s",s1.password);
            fprintf(userptr,"%s\n",s1.password);


        }
        fclose(userptr);
return s1.userId;
}

//user input the product details

int productDetails(int userId)
{
    FILE *productptr=fopen("product.txt","a+");
    char pName[10];
    int noOfProduct;
    if(productptr==NULL)
    {
        printf("file does not exist");
        exit(0);
    }
    
    // write the product details in the file
    
    else
    {
        printf("\n enter product name:");
        scanf("%s",pName);
        fflush(stdin);
        fprintf(productptr,"%d \t %s ",userId,pName);
        
        printf("\n enter the no of product items which user want to donate:");
        scanf("%d",&noOfProduct);
		fprintf(productptr,"\t%d\n",noOfProduct);
    }
    fclose(productptr);
    return noOfProduct;
}

//enter the NGO details

void NGO_details()
{
	FILE *ngoptr=fopen("NGO.txt","a+");
	char nName[10],nAddress[50];
	if(ngoptr==NULL)
	{
	    printf("file does not exist");
	    exit(0);
	}
	else
	{
	 	printf("\n enter NGO name:");
	    scanf("%s",nName);
	    printf("\n enter NGO address:");
	    fflush(stdin);
	    scanf("%s",nAddress);
	    fprintf(ngoptr,"%s \t %s\n",nName,nAddress);

	}
fclose(ngoptr);
}

//NGO places the requirements

void ngoReq( )
{
	char receiveName[10];
	int no,userid;

	struct product
	{
		char pName[10];
		int Pno;
	}pobj;
	
	FILE *ptr=fopen("product.txt","r");
	
	if(ptr==NULL)
	{
		printf("file does not exist");
		exit(0);
	}
	
	printf("\n enter the required product name:");
	scanf("%s",pobj.pName);

    // for checking product is present or not

	while(fscanf(ptr,"%d %s %d",&userid,receiveName,&no)!=EOF)
	{
		if(strcmp(pobj.pName,receiveName)==0)
		{
			//product is found ...it displays the details to the NGO
  			printf("\n donated products to company are:");
			printf("%s",receiveName);
			printf("\t%d",no);
		
		}
		     
	}
	
}


int main()
{

    int access,ret_pass,uid,noprod,userid;
    
    printf("\n are you a new user:");
    printf("\n1]yes=1 \n 2]no=0 \n");
    scanf("%d",&access);
    
    //if entered value is 1 ,then it is user..otherwise entered value is 2,then it is NGO
    if(access==TRUE)
    {
		printf("\n enetr user id:");
    	scanf("%d",&userid);
        uid=NEW_Login(userid);
        printf("\n enter product details:");

        noprod=productDetails(uid);
        
		printf("\n company accept the product");
    }
	else
	{ 
		NGO_details();
		ngoReq();
	}
 return 0;
}



