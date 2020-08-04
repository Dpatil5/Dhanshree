#include <stdio.h>
#include <stdlib.h>
#include<string.h>
#define TRUE 1
#define FAlSE 0



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
        else
        {
            printf("\n enter the name:\n");
            //s1.userId++;
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


void NGO_details()
{
FILE *ngoptr=fopen("NGO.txt","a+");
char nName[10],nAddress[50];
if(ngoptr==NULL)
{
    printf("file does not exist");
    return 0;
}
else
{
    printf("\n enter NGO name:");
    scanf("%s",nName);
    printf("\n enter NGO address:");
    scanf("%s",nAddress);
    fprintf(ngoptr,"%s \t %s\n",nName,nAddress);

}
fclose(ngoptr);
}


void ngoReq( )
{char receiveName[10];
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
	printf("\n enter te no of product required:");
	scanf("%d",pobj.Pno);
	fflush(stdin);
	while(fscanf(ptr," %s %d",receiveName,&no)!=EOF);
	{
		if(strcmp(pobj.pName,receiveName)==0)
		{
			printf("%s",receiveName);
			printf("%d",no);
		
		}
		     
	}
	
}


int main()
{

    int access,ret_pass,uid,noprod,userid;
    printf("\n are you a new user:");
    printf("\n1]yes=1 \n 2]no=0 \n");
    scanf("%d",&access);

    if(access==TRUE)
    {
		printf("\n enetr user id:");
    	scanf("%d",&userid);
        uid=NEW_Login(userid);
        printf("\n enter product details:");

        noprod=productDetails(uid);
        //printf("%d",noprod);

printf("\n company accept the product");
    }
else{ 
NGO_details();
ngoReq();
}
 return 0;
}



