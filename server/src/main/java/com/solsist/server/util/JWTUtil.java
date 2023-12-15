//package com.solsist.server.util;
//
//
//import com.alibaba.fastjson2.JSONObject;
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTCreator;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.interfaces.Claim;
//
//import java.lang.reflect.Field;
//import java.lang.reflect.Modifier;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.Map;
//
//public class JWTUtil {
//    private static String secretKey = "LKJ12390@)(#*1o2uj3lk12gjkKJDHASKJJKHSD";
//
//    /**
//     * 生成签名
//     *
//     * @return 加密的Token
//     */
//    public static String createSign(Object obj, Integer expire) {
//        Date iatDate = new Date();
//        Calendar nowTime = Calendar.getInstance();
//        nowTime.add(Calendar.MINUTE, expire);
//        Date expiresDate = nowTime.getTime();
//        JWTCreator.Builder token = JWT.create();
//        Field[] fields = obj.getClass().getFields();
//
//        for (Field field : fields) {
//            String name = field.getName();
//
//            if (!Modifier.isStatic(field.getModifiers())) {
//                Object value = null;
//                try {
//                    value = field.get(field);
//                    token.withClaim(name, value.toString());
//                } catch (IllegalAccessException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }
//
//        return token
//                .withIssuedAt(iatDate)
//                .withExpiresAt(expiresDate)
//                .sign(Algorithm.HMAC256(secretKey));
//    }
//
//    /**
//     * 校验Token是否正确
//     *
//     * @return 是否正确
//     */
//    public static TokenDTO verifyToken(String token) {
//        //根据传来的Token获取Seed
//        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey)).build();
//        TokenDTO tokenDTO = new TokenDTO();
//        try {
//            Map<String, Claim> claims = verifier.verify(token).getClaims();
//            Claim seed_claim = claims.get("seed");
//            String jsonSign = seed_claim.asString();
//            JSONObject jsonObject = JSONObject.parseObject(jsonSign);
//            tokenDTO.setAccountId(jsonObject.getLong("accountId"));
//            tokenDTO.setRoleId(jsonObject.getInteger("roleId"));
//            tokenDTO.setSchoolId(jsonObject.getLong("schoolId"));
//        } catch (Exception e) {
//            tokenDTO = null;
//        }
//        return tokenDTO;
//    }
//
//    /**
//     * 无需secret解密，获得Token中的Seed信息
//     *
//     * @return Token中包含的种子值，通常是用户ID
//     */
//    public static String getSeed(String token) {
//        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey)).build();
//        Map<String, Claim> claims = verifier.verify(token).getClaims();
//        Claim seed_claim = claims.get("seed");
//        return seed_claim.asString();
//    }
//
//    public static void main(String[] args) {
//        TokenDTO tokenDTO = new TokenDTO();
//        tokenDTO.setAccountId(1L);
//        tokenDTO.setSchoolId(1L);
//        tokenDTO.setRoleId(1);
//        String req = createSign(tokenDTO.toString(), 240000000);
//
//        System.out.println(req);
//    }
//}
