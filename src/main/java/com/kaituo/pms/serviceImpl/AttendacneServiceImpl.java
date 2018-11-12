package com.kaituo.pms.serviceImpl;

import com.kaituo.pms.bean.Attendance;
import com.kaituo.pms.bean.AttendanceExample;
import com.kaituo.pms.bean.ChangeIntegral;
import com.kaituo.pms.dao.AttendanceMapper;
import com.kaituo.pms.error.MyException;
import com.kaituo.pms.service.AttendacneService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AttendacneServiceImpl implements AttendacneService {

    @Autowired
    private AttendanceMapper attendanceMapper;

    @Override
    public boolean uploadExcel(String fileName, MultipartFile file) throws IOException {
        boolean notNull = false;

        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new MyException("上传文件格式不正确");
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);//
        if(sheet!=null){
            notNull = true;
        }
        //开始操作Excel表格
        Attendance attendance = new Attendance();
        Row row2 = sheet.getRow(2);
        Cell cell = row2.getCell(2);
        String time = cell.toString();
        String substring = time.substring(0,8);
        attendance.setDatatime(time);
        Map<String,String> map = new HashMap<>();

        Integer lastRowNum = Integer.valueOf(time.substring(time.length()-2));

        for (int i = 4 ; i <= sheet.getLastRowNum(); i++){

            if(i%2 == 0){
                Row row = sheet.getRow(i);

                attendance.setJobnumber(row.getCell(2).toString());
                attendance.setName(row.getCell(10).toString());
                attendance.setDeptname(row.getCell(20).toString());

                // System.out.println(attendance);
            }else {
                //TODO 这里面可以判断一下错误的时间格式2
                //这里是时间！！！
                Row row = sheet.getRow(i);

                for(int a = 0; a<lastRowNum;a++){
                    //这里是调用公式的方法！
                    //记得需要把当前日期获取到  当前日期为 a+1
                    String key ;
                    if(a < 9){
                        key= substring+"0"+(a+1);
                    }else {
                        key = substring + (a+1);
                    }

                    map.put(key,row.getCell(a ).toString());

                }
                //System.out.println(map);
                attendance.setAttendancedata(map.toString());
                if(i%2 != 0){
                    //System.err.println(attendance);
                    attendanceMapper.insertSelective(attendance);
                }
            }
        }
        return notNull;
    }

    @Override
    public Attendance selectById(int i) {
        return attendanceMapper.selectByPrimaryKey(i);
    }

    /**
     * 更新Attendacne的数据
     * @param attendance
     */
    @Override
    public void updateByExample(Attendance attendance) {
        AttendanceExample example = new AttendanceExample();
        AttendanceExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(attendance.getId());
        attendance.setId(null);
        attendanceMapper.updateByExampleSelective(attendance,example);
    }

    /**
     * 查询attendance表中所有数据
     * @return
     */
    @Override
    public List<Attendance> selectAll() {
        List<Attendance> attendances = attendanceMapper.selectAll();
        return attendances;
    }

    /**
     * 查询attendance所有数据
     * @return
     */
    @Override
    public List<Attendance> selectByExample() {
        return attendanceMapper.selectByExampleWithBLOBs(null);
    }

    @Override
    public List<Object> uploadExcelToIntergral(String fileName, MultipartFile file) throws  IOException {



        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new MyException("上传文件格式不正确");
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);//
        if(sheet==null){
            return null;
        }
        //开始操作Excel表格
        List<Object> allList = new ArrayList<>();
        System.out.println("一共有多少行="+sheet.getLastRowNum());
        for (int i = 0;i<=sheet.getLastRowNum();i++){
            Row row = sheet.getRow(i);

            ChangeIntegral ci = new ChangeIntegral();

            ci.setId((int)row.getCell(0).getNumericCellValue());
            ci.setName(String.valueOf(row.getCell(1)));
            ci.setInteger((int)row.getCell(2).getNumericCellValue());
            allList.add(ci);
        }

        return allList;
    }
}