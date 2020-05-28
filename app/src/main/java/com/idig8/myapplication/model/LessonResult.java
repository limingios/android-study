package com.idig8.myapplication.model;

import java.util.List;

public class LessonResult {
    private int status;
    private List<Lesson> mLessons;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Lesson> getmLessons() {
        return mLessons;
    }

    public void setmLessons(List<Lesson> mLessons) {
        this.mLessons = mLessons;
    }

    @Override
    public String toString() {
        return "LessonResult{" +
                "status=" + status +
                ", mLessons=" + mLessons +
                '}';
    }

    public static class Lesson{
        private int mID;
        private int mLearnerNumber;
        private String mName;
        private String mSmallPictureUrl;
        private String mBigPictureUrl;
        private String mDescription;

        public int getmID() {
            return mID;
        }

        public void setmID(int mID) {
            this.mID = mID;
        }

        public int getmLearnerNumber() {
            return mLearnerNumber;
        }

        public void setmLearnerNumber(int mLearnerNumber) {
            this.mLearnerNumber = mLearnerNumber;
        }

        public String getmName() {
            return mName;
        }

        public void setmName(String mName) {
            this.mName = mName;
        }

        public String getmSmallPictureUrl() {
            return mSmallPictureUrl;
        }

        public void setmSmallPictureUrl(String mSmallPictureUrl) {
            this.mSmallPictureUrl = mSmallPictureUrl;
        }

        public String getmBigPictureUrl() {
            return mBigPictureUrl;
        }

        public void setmBigPictureUrl(String mBigPictureUrl) {
            this.mBigPictureUrl = mBigPictureUrl;
        }

        public String getmDescription() {
            return mDescription;
        }

        public void setmDescription(String mDescription) {
            this.mDescription = mDescription;
        }

        @Override
        public String toString() {
            return "Lesson{" +
                    "mID=" + mID +
                    ", mLearnerNumber=" + mLearnerNumber +
                    ", mName='" + mName + '\'' +
                    ", mSmallPictureUrl='" + mSmallPictureUrl + '\'' +
                    ", mBigPictureUrl='" + mBigPictureUrl + '\'' +
                    ", mDescription='" + mDescription + '\'' +
                    '}';
        }
    }
}
