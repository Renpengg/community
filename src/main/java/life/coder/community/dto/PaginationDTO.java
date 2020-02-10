package life.coder.community.dto;

import life.coder.community.model.Question;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO<T> {
    private List<T> data;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    public void setPagination(Integer totalCount, Integer page, Integer size) {
        //计算总页数
        if(totalCount % size == 0){
            totalPage = totalCount / size;
        }else{
            totalPage = totalCount / size + 1;
        }

        this.page = page;
        pages.add(page);
        for(int i=1;i<=3;i++){
            if(page - i > 0){
                pages.add(0, page - i);
            }

            if(page + i < totalPage){
                pages.add(page + i);
            }
        }

        if(page == 1){
            showPrevious = false;
        }else{
            showPrevious = true;
        }

        if(page == totalPage){
            showNext = false;
        }else{
            showNext = true;
        }

        if(pages.contains(1)){
            showFirstPage = false;
        }else{
            showFirstPage = true;
        }

        if(pages.contains(totalPage)){
            showEndPage = false;
        }else{
            showEndPage = true;
        }
    }
}
