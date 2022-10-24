
package oit.is.z1078.kaizi.janken.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z1078.kaizi.janken.model.Entry;

/**
 * sample21Controller
 *
 * クラスの前に@Controllerをつけていると，HTTPリクエスト（GET/POSTなど）があったときに，このクラスが呼び出される
 */
@Controller
public class JankenController {

  @Autowired
  private Entry entry;
  /**
   * work02part1というGETリクエストがあったら work02part1()を呼び出し，work02part1.htmlを返す
   */
  /*
   * @GetMapping("/janken")
   * public String janken() {
   * return "janken.html";
   * }
   */

  /**
   * パスパラメータ2つをGETで受け付ける 1つ目の変数をparam1という名前で，2つ目の変数をparam2という名前で受け取る
   * GETで受け取った2つの変数とsample22()の引数の名前が同じなため， 引数の前に @PathVariable と付けるだけで，パスパラメータの値を
   * javaで処理できるようになる ModelMapはthymeleafに渡すためのMapと呼ばれるデータ構造を持つ変数
   * Mapはkeyとvalueの組み合わせで値を保持する
   *
   * @param param1
   * @param model
   * @return
   */
  @GetMapping("/janken1/{param1}")
  public String janken(@PathVariable String param1, ModelMap model) {
    int i = Integer.parseInt(param1);// param1が文字列なので，parseIntでint型の数値に変換する
    // グー：１，チョキ：２，パー：３
    String te = null, result = null;
    if (i == 1) {
      te = "Gu";
      result = "draw...";
    } else if (i == 2) {
      te = "Choki";
      result = "You Lose...";
    } else if (i == 3) {
      te = "Pa";
      result = "You Win!";
    }
    // ModelMap型変数のmodelにtasuResult1という名前の変数で，tasuResultの値を登録する．
    // ここで値を登録するとthymeleafが受け取り，htmlで処理することができるようになる
    model.addAttribute("te", te);
    model.addAttribute("result", result);
    return "janken.html";

  }

  /**
   * POSTを受け付ける場合は@PostMappingを利用する /sample25へのPOSTを受け付けて，FormParamで指定された変数(input
   * name)をsample25()メソッドの引数として受け取ることができる
   *
   * @param username
   * @return
   */
  @PostMapping("/janken")
  public String jankenpost(@RequestParam String username, ModelMap model) {

    model.addAttribute("username", username);
    return "janken.html";
  }

  /**
   *
   * @param model Thymeleafにわたすデータを保持するオブジェクト
   * @param prin  ログインユーザ情報が保持されるオブジェクト
   * @return
   */
  @GetMapping("/janken")
  public String entryJanken(Principal prin, ModelMap model) {
    String loginUser = prin.getName();
    this.entry.addUser(loginUser);
    model.addAttribute("entry", this.entry);

    return "janken.html";
  }

}
