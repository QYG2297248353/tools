package com.ms.resources.file.factory;

import com.ms.core.base.basic.Lists;
import com.ms.core.base.basic.Strings;
import com.ms.core.exception.base.MsToolsException;
import com.ms.resources.file.enums.LevelEnum;
import com.ms.resources.file.enums.VolumeSizeEnum;
import com.sun.istack.internal.NotNull;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.Charset;

public class CompressionFactory {

    protected static File createCompression(File sourceDir, String zipFileDir, String fileName, String suffix) throws MsToolsException {
        if (Strings.isBlank(fileName)) {
            fileName = sourceDir.getName();
            if (sourceDir.isFile()) {
                fileName = fileName.substring(0, fileName.lastIndexOf("."));
            }
        }
        String zipFilePath = zipFileDir + File.separator + fileName + suffix;
        File zipFile = new File(zipFilePath);
        if (zipFile.exists() && zipFile.isFile() && zipFile.length() > 0) {
            throw new MsToolsException("压缩文件已存在");
        }
        return zipFile;
    }

    protected static <T extends ArchiveOutputStream> void addFileCompression(T stream, File source, String fileName) throws MsToolsException {
        if (!source.exists()) {
            throw new MsToolsException("压缩文件或目录不存在");
        }
        try {
            if (source.isDirectory()) {
                File[] files = source.listFiles();
                if (files == null || files.length == 0) {
                    return;
                }
                for (File file : files) {
                    addFileCompression(stream, file, fileName + File.separator + file.getName());
                }
            } else {
                stream.putArchiveEntry(new ZipArchiveEntry(source, fileName));
                stream.write(FileUtils.readFileToByteArray(source));
                stream.closeArchiveEntry();
            }
        } catch (Exception e) {
            throw new MsToolsException(e);
        }
    }

    protected static String checkZipDir(String source, String zip) throws MsToolsException {
        if (Strings.isBlank(zip)) {
            File sourceFile = new File(source);
            zip = sourceFile.getParent();
        }
        File file = new File(zip);
        file.mkdirs();
        if (!file.canWrite()) {
            throw new MsToolsException("压缩文件保存目录不可写");
        }
        return zip;
    }

    protected static File checkSourceDir(String source) throws MsToolsException {
        if (Strings.isBlank(source)) {
            throw new MsToolsException("压缩文件或目录路径不能为空");
        }
        File file = new File(source);
        if (!file.exists()) {
            throw new MsToolsException("压缩文件或目录不存在");
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null || files.length == 0) {
                return new File(source);
            }
            for (File f : files) {
                checkSourceDir(f.getAbsolutePath());
            }
        } else {
            if (!file.canRead()) {
                throw new MsToolsException("压缩文件不可读");
            }
        }
        return new File(source);
    }


    protected static ZipFile createZip4JPassFile(File sourceD, VolumeSizeEnum volumeSize, ZipFile zip, ZipParameters parameters) throws MsToolsException {
        if (volumeSize == null || volumeSize == VolumeSizeEnum.VOLUME_SIZE_0) {
            return createZip4JPassFile(sourceD, zip, parameters);
        }
        return createZip4J(sourceD, volumeSize, zip, parameters);
    }

    @NotNull
    private static ZipFile createZip4J(File sourceD, VolumeSizeEnum volumeSize, ZipFile zip, ZipParameters parameters) throws MsToolsException {
        try {
            if (sourceD.exists() && sourceD.isFile()) {
                zip.createSplitZipFile(Lists.of(sourceD), parameters, true, volumeSize.getSize());
            } else {
                zip.createSplitZipFileFromFolder(sourceD, parameters, true, volumeSize.getSize());
            }
        } catch (Exception e) {
            throw new MsToolsException(e);
        }
        return zip;
    }

    private static ZipFile createZip4JPassFile(File sourceD, ZipFile zip, ZipParameters parameters) throws MsToolsException {
        try {
            if (sourceD.exists() && sourceD.isFile()) {
                zip.addFile(sourceD, parameters);
            } else {
                zip.addFolder(sourceD, parameters);
            }
        } catch (Exception e) {
            throw new MsToolsException(e);
        }
        return zip;
    }

    protected static ZipFile createZip4JZipFile(File sourceD, VolumeSizeEnum volumeSize, ZipFile zip, ZipParameters parameters) throws MsToolsException {
        if (volumeSize == null || volumeSize == VolumeSizeEnum.VOLUME_SIZE_0) {
            return createZip4JZipFile(sourceD, zip, parameters);
        }
        return createZip4J(sourceD, volumeSize, zip, parameters);
    }

    private static ZipFile createZip4JZipFile(File sourceD, ZipFile zip, ZipParameters parameters) throws MsToolsException {
        try {
            if (sourceD.exists() && sourceD.isFile()) {
                zip.addFile(sourceD, parameters);
            } else {
                zip.addFolder(sourceD, parameters);
            }
        } catch (Exception e) {
            throw new MsToolsException(e);
        }
        return zip;
    }


    private static ZipParameters getZip4JPassParam(boolean pass, LevelEnum level) {
        ZipParameters zipParameters = new ZipParameters();
        if (pass) {
            zipParameters.setEncryptFiles(true);
            zipParameters.setEncryptionMethod(EncryptionMethod.AES);
            zipParameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);
        }
        if (level != null) {
            zipParameters.setCompressionLevel(level.covertCompressionLevel());
        }
        return zipParameters;
    }

    protected static ZipParameters setZipConfig(ZipFile zip, String comment, LevelEnum level, Charset encoding, boolean pass) throws MsToolsException {
        if (Strings.isNotBlank(comment)) {
            try {
                zip.setComment(comment);
            } catch (ZipException e) {
                throw new MsToolsException(e);
            }
        }
        if (encoding != null) {
            zip.setCharset(encoding);
        }
        if (pass) {
            zip.setUseUtf8CharsetForPasswords(true);
        }
        return getZip4JPassParam(pass, level);
    }
}
