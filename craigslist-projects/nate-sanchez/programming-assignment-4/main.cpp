#include <string>
#include <fstream>

class FileUtilities {
public:
    static std::string head(const std::string &file, unsigned int lines = 3);
    static std::string tail(const std::string &file, unsigned int lines = 3);
};

std::string FileUtilities::head(const std::string& file, unsigned int lines) {
    std::string result, temp;
    std::ifstream fs(file);
    for (int i = 0; i < lines && std::getline(fs, temp); i++) {
        result += temp + '\n';
    }
    return result;
}

std::string FileUtilities::tail(const std::string& file, unsigned int lines) {
    std::fstream fs(file);
    fs.seekg(0, fs.end);                                                    
    
    lines++;
    while (fs.tellg() != 0 && lines) {                                            
        fs.seekg(-1, fs.cur);                                               
        if (fs.peek() == '\n')                                                
            lines--;                                                                
    }                                                                           

    if (fs.peek() == '\n') 
        fs.seekg(1, fs.cur);                                                

    std::string result, line;                                                           
    while (std::getline(fs, line))                                            
        result += line + '\n';
    return result;
}
