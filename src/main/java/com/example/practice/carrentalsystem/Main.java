package com.example.practice.carrentalsystem;

public class Main {

    public static void main(String[] args) {

//
        
    }

    public static String getPageString(int totalPage, int currPage){

        String pageStr = "";
        int totalCount = 3;
        if(currPage==1){

            while(currPage<=totalPage && currPage<=3){
                pageStr+=currPage;
                currPage++;
            }

            if(currPage<totalPage) pageStr+="..."+totalPage;

        } else if(currPage == totalPage){

            while(currPage>0 && currPage > totalPage-totalCount){
                pageStr=currPage+pageStr;
                currPage--;
            }

            if(currPage>0) pageStr="..."+pageStr;
        } else {
            pageStr = (currPage-1)+""+currPage+""+(currPage+1);
            if(currPage-1>1) pageStr = "1"+pageStr;
            else if(currPage-1>2) pageStr = "1..."+pageStr;
            else if(currPage+1 <totalPage) pageStr+= totalPage;
            else pageStr+="..."+totalPage;
        }

        return pageStr;
    }
}
