//package suktha.export;
//
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import suktha.dao.EmployeeDao;
//import suktha.model.Employee;
//
//@WebServlet("/export")
//public class ExcelExportServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//   @Override
//protected void doGet(HttpServletRequest request, HttpServletResponse response)
//        throws ServletException, IOException {
//    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//    response.setHeader("Content-Disposition", "attachment; filename=employees.xlsx");
//
//    try {
//         String idsParam = request.getParameter("ids");
//
//    if (idsParam != null && !idsParam.isEmpty()) {
//        List<String> employeeIds = new ArrayList<>();
//        String[] idArray = idsParam.split(",");
//        for (String idStr : idArray) {
//            if (idStr.equals("on")) {
//                continue;
//            }
//            employeeIds.add(idStr);
//        }
//
//        List<Employee> employees = EmployeeDao.getEmployeesByIds(employeeIds);
//
//            try (XSSFWorkbook workbook = new XSSFWorkbook()) {
//                org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Employees");
//
//                Row headerRow = sheet.createRow(0);
//                String[] headers = {"Employee Id", "First Name", "Last Name", "E-mail", "Date of Birth", "location",
//                        "Phone No", "Gender", "Job", "Salary", "Manager", "Project", "Status"};
//                for (int i = 0; i < headers.length; i++) {
//                    Cell cell = headerRow.createCell(i);
//                    cell.setCellValue(headers[i]);
//                }
//
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//                int rowNum = 1;
//                for (Employee employee : employees) {
//                    Row row = sheet.createRow(rowNum++);
//                    row.createCell(0).setCellValue(employee.getEmployeeId());
//                    row.createCell(1).setCellValue(employee.getFirstName());
//                    row.createCell(2).setCellValue(employee.getLastName());
//                    row.createCell(3).setCellValue(employee.getEmail());
//                    row.createCell(4).setCellValue(dateFormat.format(employee.getDob()));
//                    row.createCell(5).setCellValue(employee.getLocation());
//                    row.createCell(6).setCellValue(employee.getPhoneNo());
//                    row.createCell(7).setCellValue(employee.getGender());
//                    row.createCell(8).setCellValue(employee.getJob());
//                    row.createCell(9).setCellValue(employee.getSalary());
//                    row.createCell(10).setCellValue(employee.getManager());
//                    row.createCell(11).setCellValue(employee.getProject());
//                    row.createCell(12).setCellValue(employee.getEmpStatus().toString());
//                }
//
//                workbook.write(response.getOutputStream());
//                response.sendRedirect("list");
//
//            } catch (IOException | NullPointerException | IllegalArgumentException e) {
//                // Handle IOException, NullPointerException, or IllegalArgumentException
//                e.printStackTrace(); // Print stack trace for debugging
//                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error exporting data");
//            }
//        } else {
//                response.sendError(HttpServletResponse.SC_NOT_FOUND, "No records found");
//        }
//    } catch (NumberFormatException e) {
//        // Handle NumberFormatException
//        e.printStackTrace(); // Print stack trace for debugging
//        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid parameter value");
//    }
//}
//
//}