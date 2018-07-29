package com.mssoftech.web.util;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FileUtil {
//    public static void createZip(File[] files, String zipfile) {
//
//        ZipOutputStream zos = null;
//        try {
//            zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(new File(zipfile))));
//            createZip(zos, files);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            IOUtils.closeQuietly(zos);
//        }
//    }
//    public static void createZip(ZipOutputStream zos, File[] files) throws IOException {
//        byte[] buf = new byte[1024];
//        InputStream is = null;
//        try {
//            for (File file : files) {
//                ZipEntry entry = new ZipEntry(file.getName());
//                zos.putNextEntry(entry);
//                is = new BufferedInputStream(new FileInputStream(file));
//                int len = 0;
//                while ((len = is.read(buf)) != -1) {
//                    zos.write(buf, 0, len);
//                }
//            }
//        } finally {
//            IOUtils.closeQuietly(is);
//        }
//    }
	public static boolean deleteFiele(String filename) {
		File newfile = new File(filename);
		newfile.delete();
		File file = new File(filename);
		if (file.exists()) {
			// System.out.println("削除失敗");
			return false;
		}
		return true;
	}

	public static boolean renameFiele(String oldfilename, String newfilename) {
		File file1 = new File(oldfilename);
		File file2 = new File(newfilename);

		if (file1.renameTo(file2)) {
			// System.out.println("移動成功");
			return true;
		} else {
			// System.out.println("移動失敗");
			return false;
		}
	}

	public static String[][] readCsv(String filename,String encoding) {
		String content = readFileAll(filename,encoding);
		String[] lines = null;
		lines = content.split("\n");
		ArrayList<String[]> ar = new ArrayList();
		for (int i = 0; i < lines.length; i++) {
			String[] cols = lines[i].split("\t");
			ar.add(cols);
		}

		return (String[][]) ar.toArray(new String[][] {});

	}

	public static String readFileAll(String filename, String encoding) {
		String content = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), encoding));
			String str;
			StringBuffer sb = new StringBuffer();
			while ((str = br.readLine()) != null) {
				sb.append(str + "\n");
			}
			br.close();
			content = sb.toString();
		} catch (IOException e) {
			return null;
		}
		return content;
	}

	public static BufferedReader openBufferedReader(String filename, String encoding) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), encoding));
			return br;
		} catch (Exception e) {
			return null;
		}
	}

	public static void writeFile(String filename, String s,String encoding) {
		try {
			BufferedWriter bw = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(filename), encoding));
			bw.write(s);
			bw.close();
		} catch (IOException e) {
		}

	}

	public static String createFileName() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMddhhmmss"));

	}

	
	public static Integer[] getTabHeaderCol(String headerLine, String[] strings) {
		String[] cols = headerLine.split("\t");
		Integer[] icols=new Integer[strings.length];
		for(int i=0;i<strings.length;i++){
			String hdr=strings[i];
			for (int j=0;j<cols.length;j++){
				if (cols[j].equals(hdr)){
					icols[i]=j;
				}
			}
		}
		return icols;
	}

	public static int getMaxCol(Integer[] headerCol) {
		int maxcol=-1;
		for (int i=0;i<headerCol.length;i++){
			if (headerCol[i]!=null){
				if (headerCol[i].intValue()>maxcol){
					maxcol=headerCol[i];
				}
			}
		}
		return maxcol;
	}
}
