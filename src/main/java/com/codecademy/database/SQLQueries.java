package com.codecademy.database;

public class SQLQueries {

    public String getAllUsers(){
        return "SELECT * FROM [User]";
    }
    public String getAllCourses(){
        return "SELECT * FROM [Course]";
    }
    public String getAllCoursesWhereNotEnrolled(){
        return "SELECT * FROM [Course] WHERE courseName NOT IN (SELECT courseName FROM [Enrollment] WHERE email != ?)";
    }

    public String insertUser(){
        return  "INSERT INTO [User](name, email, dateOfBirth, gender, address, zip, country) VALUES (?, ?, ?, ?, ?, ?, ?)";
    }


    public String addUserToCourse(){
        return  "INSERT INTO [Enrollment](email, courseName, enrollmentDate) VALUES (?, ?, ?)";
    }
    public String removeUserFromCourse(){
        return  "DELETE FROM [Enrollment] WHERE courseName = ? AND email = ?";
    }


    public String updateUser(){
        return   "UPDATE [User] SET name = ?, email = ?, dateOfBirth = ?, gender = ?, address = ?, zip = ?, country = ? WHERE email = ?";
    }
    public String removeUser(){
        return   "DELETE FROM [User] WHERE email = ?";
    }
    public String getCertificatesForUser(){
        return   "SELECT * FROM [Certificate] WHERE email = ?";
    }

    public String getCoursesForUser(){
        return  "SELECT * FROM [Course] WHERE courseName IN (SELECT courseName FROM [Enrollment] WHERE email = ?)";
    }
    public String addCourse(){
        return  "INSERT INTO [Course](courseName, introText, topic, level) VALUES (?, ?, ?, ?)";
    }

    public String updateCourse(){
        return  "UPDATE [Course] SET courseName = ?, introText = ?, topic = ?, level = ? WHERE courseName = ?";
    }
    public String deleteCourse(){
        return  "DELETE FROM [Course] WHERE courseName = ?";
    }

    public String getModulesForCourse(){
        return  "SELECT * FROM [Module] LEFT JOIN [ContentItem] ON [Module].contentId = [ContentItem].contentId WHERE courseName = ?";
    }
    public String getAvailableModules(){
        return  "SELECT * FROM [Module] LEFT JOIN [ContentItem] ON [Module].contentId = [ContentItem].contentId WHERE courseName IS NULL";
    }


    public String addModuleToCourse(){
        return  "UPDATE [Module] SET courseName = ? WHERE contentId = ?";
    }


    public String getAvgCompletionRateModule(){
        return  "SELECT AVG(viewedPercentage) as avgCompletionRate FROM [Viewed] WHERE contentId = ?";
    }


    public String getAmountCompleted(){
        return  "SELECT courseName, count(*) AS totalCertificates FROM Certificate WHERE courseName = ? group by courseName";
    }
    public String getMaleCompletionRate(){
        return  "SELECT COUNT(*) AS amountCompleted FROM [Certificate] WHERE courseName = ? AND email IN (SELECT email FROM [User] WHERE gender = 'Male')";
    }
    public String getMaleCompletionAmount(){
        return  "SELECT COUNT(*) AS amountTotal FROM [Enrollment] WHERE email IN (SELECT email FROM [User] WHERE gender = 'Male')";
    }


    public String getFemaleCompletionRate(){
return "SELECT COUNT(*) AS amountCompleted FROM [Certificate] WHERE courseName = ? AND email IN (SELECT email FROM [User] WHERE gender = 'Female')";
    }
    public String getFemaleCompletionAmount(){
return "SELECT COUNT(*) AS amountTotal FROM [Enrollment] WHERE email IN (SELECT email FROM [User] WHERE gender = 'Female')";
    }

    public String getAllWebcasts(){
        return  "SELECT * FROM [Webcast] LEFT JOIN [ContentItem] ON [Webcast].contentId = [ContentItem].contentId";

    }
    public String getCourseFromName(){
        return  "SELECT TOP(1) * FROM [Course] WHERE courseName = ?";

    }
    public String getTopWebcasts(){
        return  "SELECT * FROM [Webcast] LEFT JOIN [ContentItem] ON [Webcast].contentId = [ContentItem].contentId WHERE Webcast.contentId IN (SELECT TOP(3) contentId FROM [Viewed] GROUP BY contentId ORDER BY COUNT(contentId) DESC)";

    }



}
