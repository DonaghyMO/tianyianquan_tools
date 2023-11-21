package com.tianyianquan;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TianyianquanToolsApplicationTests {
//    @Autowired
//    private ExecuteDao exeDao;
//    @Test
//    void contextLoads() {
//
//        try {
//            String command = "nmap -v baidu.com -p 8080-9000";
//            // 执行命令
//            Process process = Runtime.getRuntime().exec(command);
//
//            // 获取命令执行结果
//            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//
//            // 等待进程执行完成
//            int exitCode = process.waitFor();
//            if (exitCode == 0) {
//                System.out.println("命令执行成功");
//            } else {
//                System.out.println("命令执行失败");
//            }
//        } catch (InterruptedException | IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testDao(){
//        ResultBean res = new ResultBean("halo","nmap -v baidu.com -p 1-2000", Code.EXE_ERR);
//        exeDao.save(res);
////        exeDao.getAll();
//    }
//
//    @Test
//    public void testGet(){
//        ArrayList<ResultBean> arr;
//        arr = (ArrayList<ResultBean>) exeDao.getAll();
//        System.out.println(arr.get(0).toString());
//
//    }
//    @Test
//    public void testLog(){
//        Path path = Paths.get("/Users/donaghymo/Desktop/test.log");
//        try {
//            // 写入文件
//            Files.write(path, "Hello, this is some text!".getBytes());
//
//            // 读取文件
//            List<String> lines = Files.readAllLines(path);
//            for (String line : lines) {
//                System.out.println(line);
//            }
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }


}
