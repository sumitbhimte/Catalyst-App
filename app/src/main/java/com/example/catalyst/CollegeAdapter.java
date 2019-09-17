
//for RecyclerView we will create
package com.example.catalyst;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CollegeAdapter extends RecyclerView.Adapter<CollegeAdapter.ProductViewHolder> {

    private Context mCtx;
    private List<College> collegeList;

    public CollegeAdapter(Context mCtx, List<College> collegeList) {
        this.mCtx = mCtx;
        this.collegeList = collegeList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.college_list, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        College college = collegeList.get(position);

        //loading the image
        Glide.with(mCtx)
                .load(college.getImage())
                .into(holder.imageView);

        holder.textViewName.setText(college.getCollege_name());
        holder.textViewCourse.setText(college.getCollege_course());
        holder.textViewLoc.setText(String.valueOf(college.getCollege_location()));
        holder.textViewWebsite.setText(String.valueOf(college.getCollege_website()));
        holder.textViewCode.setText(String.valueOf(college.getCollege_code()));

    }

    @Override
    public int getItemCount() {
        return collegeList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {


        TextView  textViewName,textViewCourse, textViewLoc, textViewCode,textViewWebsite;
        ImageView imageView;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewName);
            textViewCourse = itemView.findViewById(R.id.textViewCourse);
            textViewLoc = itemView.findViewById(R.id.textViewLoc);
            textViewWebsite = itemView.findViewById(R.id.textViewWebsite);
            textViewCode = itemView.findViewById(R.id.textViewCode);
            imageView = itemView.findViewById(R.id.imageView);

        }
    }
}