import com.itmo.soa.models.mappers.RouteMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @program: SOA-Lab2-Service1
 * @description:
 * @author: Siyuan
 * @create: 2022-10-14 16:55
 **/
public class MyBatisTest {

    @Test
    public void test() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        RouteMapper routeMapper = sqlSession.getMapper(RouteMapper.class);
        System.out.println(routeMapper.findRouteById(2).toString());

    }
}
